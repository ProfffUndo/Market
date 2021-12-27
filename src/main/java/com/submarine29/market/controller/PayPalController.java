package com.submarine29.market.controller;

import com.submarine29.market.domain.Order;
import com.submarine29.market.domain.OrderItem;
import com.submarine29.market.domain.Status;
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
@RequestMapping("/basket")
public class PayPalController {
    @Autowired
    PayPalService service;

    public static final String SUCCESS_URL = "pay/success";
    public static final String CANCEL_URL = "pay/cancel";

    @PostMapping("/pay/{id}")
    public String payment(@PathVariable("id") Order order) {
        try {

            BasketService.checkAmountOfProductBeforePayment(order);
            BasketService.addPopularityToProductsFromOrder(order);

            List<OrderItem> orderItems = order.getOrderItems();
            double total = 0.00;

            order.setStatus(Status.CONFIRMED);

            for (OrderItem orderItem : orderItems){
                total = total + orderItem.getProduct().getPrice();
            }

            Payment payment = service.createPayment(total, "http://localhost:8080/" + CANCEL_URL,
                    "http://localhost:8080/" + SUCCESS_URL);
            for(Links link:payment.getLinks()) {
                if(link.getRel().equals("approval_url")) {
                    return "redirect:"+link.getHref();
                }
            }
        } catch (PayPalRESTException e) {

            e.printStackTrace();
        } catch (IllegalArgumentException e){
            return "error/error";
           // throw new IllegalArgumentException("Недостаточно товара на складе",e);
        }
        return "redirect:/";
    }

    @GetMapping(value = CANCEL_URL)
    public String cancelPay() {
        return "cancel";
    }

    @GetMapping(value = SUCCESS_URL)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = service.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                return "success";
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/";
    }
}
