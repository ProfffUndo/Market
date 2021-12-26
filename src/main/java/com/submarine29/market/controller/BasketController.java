package com.submarine29.market.controller;

import com.submarine29.market.domain.Order;
import com.submarine29.market.domain.OrderItem;
import com.submarine29.market.domain.User;
import com.submarine29.market.repo.OrderRepo;
import com.submarine29.market.repo.UserDetailsRepo;
import com.submarine29.market.services.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/basket")
public class BasketController {

    @Autowired
    private final OrderRepo orderRepo;

    @Autowired
    private final UserDetailsRepo userRepo;

    @Autowired
    private final BasketService basketService;

    public BasketController(OrderRepo repo, UserDetailsRepo userRepo, BasketService basketService) {
        this.orderRepo = repo;
        this.userRepo = userRepo;
        this.basketService = basketService;
    }

    @GetMapping("")
    public String basket(Model model,@AuthenticationPrincipal User currentUser) {
        Long orderId= basketService.getBasketOrder(currentUser.getId());
        if(orderId==null)
        {
            model.addAttribute("comment", "Корзина пуста");
            model.addAttribute("order", new Order());
        }else {
            Long basketOrderId=basketService.getBasketOrder(currentUser.getId());
            Order order=orderRepo.findById(basketOrderId).get();
            model.addAttribute("order", order);
            try{
                BasketService.checkAmountOfProductBeforePayment(order);
                model.addAttribute("calculatedSum",BasketService.calculateSum(order));
            }catch (IllegalArgumentException e){
                model.addAttribute("amountError","Недостаточно товара на складе");
            }
        }
        return "basket/basket";
    }

    @PostMapping("/add")
    public String addProductToBasket(@ModelAttribute("product_id") Long productId, @AuthenticationPrincipal User currentUser){
        try {
            basketService.addProductToOrder(currentUser.getId(), productId);
        }
        catch (IllegalArgumentException e){}
        return "redirect:/basket";
    }

    @PostMapping("/delete")
    public String deleteProductFromBasket(@ModelAttribute("product_id") Long productId, @AuthenticationPrincipal User currentUser){
        basketService.deleteProductFromOrder(currentUser.getId(),productId,false);
        return "redirect:/basket";
    }

    @PostMapping("/remove")
    public String removeProductFromBasket(@ModelAttribute("product_id") Long productId, @AuthenticationPrincipal User currentUser){
        basketService.deleteProductFromOrder(currentUser.getId(),productId,true);
        return "redirect:/basket";
    }
}
