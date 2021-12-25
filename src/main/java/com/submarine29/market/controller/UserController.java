package com.submarine29.market.controller;

import com.submarine29.market.domain.Role;
import com.submarine29.market.domain.User;
import com.submarine29.market.repo.UserDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserDetailsRepo userDetailsRepo;

    @Autowired
    public UserController(UserDetailsRepo userDetailsRepo) {
        this.userDetailsRepo = userDetailsRepo;
    }

    @GetMapping
    public String list(Model model, @AuthenticationPrincipal User currentUser) {
        if (!currentUser.getAuthorities().contains(Role.ADMIN) && !currentUser.getAuthorities().contains(Role.MANAGER)) {
            return "error/error";
        }
        model.addAttribute("users", userDetailsRepo.findAll());
        return "users/list";
    }
}
