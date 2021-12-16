package com.submarine29.market.controller;

import com.submarine29.market.domain.Role;
import com.submarine29.market.domain.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @GetMapping
    public String personalProfile(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("currentUser", user);
        model.addAttribute("option2", "no");
        return "profile/profile";
    }

    @GetMapping("{id}")
    public String profileForAny(@PathVariable("id") User user, Model model, @AuthenticationPrincipal User currentUser) {
        if (!user.getAuthorities().contains(Role.ADMIN) || !currentUser.getId().equals(user.getId())) {
            return "error/error";
        }
        model.addAttribute("currentUser", user);
        return "profile/profile";
    }
}
