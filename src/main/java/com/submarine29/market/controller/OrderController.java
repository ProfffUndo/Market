package com.submarine29.market.controller;

import com.submarine29.market.domain.Order;
import com.submarine29.market.domain.Role;
import com.submarine29.market.domain.Status;
import com.submarine29.market.domain.User;
import com.submarine29.market.repo.OrderRepo;
import com.submarine29.market.repo.UserDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
            model.addAttribute("statuses", Status.values());
        }
        model.addAttribute("orders", user.getPaidOrders());
        return "order/list";
    }

    @GetMapping("get_order/{orderId}")
    public String showOrder(@PathVariable("orderId") Order order, @AuthenticationPrincipal User currentUser,
                            Model model) {
        model.addAttribute("order", order);
        return "order/show";
    }

    @GetMapping("set_status/{orderId}")
    public String setStatus(@PathVariable("orderId") Order order, @RequestParam("status") String newStatus) {
        order.setStatus(Status.valueOf(newStatus));
        orderRepo.save(order);
        return "redirect:/orders/" + order.getUser().getId();
    }
}
