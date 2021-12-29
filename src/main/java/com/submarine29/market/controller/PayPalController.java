package com.submarine29.market.controller;

import com.submarine29.market.domain.Order;
import com.submarine29.market.domain.OrderItem;
import com.submarine29.market.domain.Status;
import com.submarine29.market.repo.OrderRepo;
import com.submarine29.market.services.BasketService;
import com.submarine29.market.services.PayPalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

import java.util.List;

@Controller
public class PayPalController {
    @Autowired
    OrderRepo orderRepo;

    @Autowired
    PayPalService service;

    public static final String SUCCESS_URL = "pay/success";
    public static final String CANCEL_URL = "pay/cancel";

    @PostMapping("basket/pay/{id}")
    public String payment(@PathVariable("id") Order order) {
        try {

            BasketService.checkAmountOfProductBeforePayment(order);

            List<OrderItem> orderItems = order.getOrderItems();
            double total = 0.00;

            //order.setStatus(Status.CONFIRMED);

            for (OrderItem orderItem : orderItems){
                total = total + orderItem.getSum();
            }

            Payment payment = service.createPayment(total, "http://localhost:8080/" + CANCEL_URL,
                    "http://localhost:8080/" + SUCCESS_URL);

            order.setPaymentId(payment.getId());

            for(Links link:payment.getLinks()) {
                if(link.getRel().equalsIgnoreCase("approval_url")) {
                    return "redirect:"+link.getHref();
                }
            }


        } catch (PayPalRESTException e) {

            e.printStackTrace();
        } catch (IllegalArgumentException e){
            return "error/error";
        }
        return "redirect:/";
    }

    @GetMapping(CANCEL_URL)
    public String cancelPay() {
        return "pay/cancel";
    }

    @GetMapping(SUCCESS_URL)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = service.executePayment(paymentId, payerId);

            if (payment.getState().equals("approved")) {
                orderRepo.findByPaymentId(paymentId).setStatus(Status.PAID);

                String deliveryAddress = payment.getPayer().getPayerInfo().getShippingAddress().getCity()+" "+
                        payment.getPayer().getPayerInfo().getShippingAddress().getLine1()+" "+
                        payment.getPayer().getPayerInfo().getShippingAddress().getLine2()+" "+
                        payment.getPayer().getPayerInfo().getShippingAddress().getPostalCode();

                orderRepo.findByPaymentId(paymentId).setDeliveryAddress(deliveryAddress.replaceAll("null",""));

                BasketService.addPopularityToProductsFromOrder(orderRepo.findByPaymentId(paymentId));
                BasketService.decreaseAmountOfProduct(orderRepo.findByPaymentId(paymentId));

                return "pay/success";
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/";
    }
}
