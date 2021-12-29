package com.submarine29.market.controller;

import com.submarine29.market.domain.Order;
import com.submarine29.market.domain.Status;
import com.submarine29.market.domain.User;
import com.submarine29.market.repo.OrderRepo;
import com.submarine29.market.repo.UserDetailsRepo;
import com.submarine29.market.services.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepo orderRepo;
    private final UserDetailsRepo userRepo;

    @Autowired
    public OrderController(OrderRepo orderRepo, UserDetailsRepo userRepo) {
        this.orderRepo = orderRepo;
        this.userRepo = userRepo;
    }

    @GetMapping("{userId}")
    public String getOrdersOfUser(@PathVariable String userId,
                                  @AuthenticationPrincipal User currentUser, Model model) {
        User user = userRepo.findById(ControllerUtil.createNormalniyIdFromUserBleat(userId)).get();
        if (!currentUser.isManager() && !currentUser.getId().equals(user.getId())) {
            return "error/error";
        }
        if (currentUser.isManager()) {

            model.addAttribute("statuses", Arrays.stream(Status.values()).filter((s)->s!=Status.NEW).collect(Collectors.toList()));
        }
        model.addAttribute("orders", user.getPaidOrders());
        return "order/list";
    }

    @GetMapping("get_order/{orderId}")
    public String showOrder(@PathVariable("orderId") Order order, @AuthenticationPrincipal User currentUser,
                            Model model) {
        if (currentUser.isManager() || Objects.equals(order.getUser().getId(), currentUser.getId())) {
            model.addAttribute("order", order);
            return "order/show";
        }
        return "error/error";
    }

    @GetMapping("set_status/{orderId}")
    public String setStatus(@PathVariable("orderId") Order order, @RequestParam("status") String newStatus) {
        order.setStatus(Status.valueOf(newStatus));
        if(newStatus.equals(Status.NEW.toString()))
            BasketService.increaseAmountOfProduct(order);
        orderRepo.save(order);
        return "redirect:/orders/" + order.getUser().getId();
    }
}
