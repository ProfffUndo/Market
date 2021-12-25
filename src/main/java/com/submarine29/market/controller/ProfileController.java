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
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UserDetailsRepo userRepo;

    @GetMapping
    public String personalProfile(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("currentUser", user);
        addUserRolesInModel(user, model);
        return "profile/profile";
    }

    @GetMapping("{id}")
    public String profileForAny(@PathVariable("id") String id, Model model, @AuthenticationPrincipal User currentUser) {
        User user = userRepo.findById(ControllerUtil.createNormalniyIdFromUserBleat(id)).get();
        if (!currentUser.getAuthorities().contains(Role.ADMIN) && !currentUser.getAuthorities().contains(Role.MANAGER)
                && !currentUser.getId().equals(user.getId())) {
            return "error/error";
        }
        model.addAttribute("currentUser", user);
        addUserRolesInModel(user, model);
        return "profile/profile";
    }

    private void addUserRolesInModel(User user, Model model) {
        if (user.getRoles().contains(Role.MANAGER)) {
            model.addAttribute("managerCheck", true);
        }
        if (user.getRoles().contains(Role.ADMIN)) {
            model.addAttribute("adminCheck", true);
        }
    }

    @PostMapping("/set_roles/{id}")
    public String setUserRoles(@PathVariable("id") String userId,
                               @ModelAttribute("manager") String manager,
                               @ModelAttribute("admin") String admin,
                               @AuthenticationPrincipal User authoredUser,
                               Model model) {
        if (!authoredUser.getAuthorities().contains(Role.ADMIN)) {
            return "error/error";
        }
        User user = userRepo.findById(ControllerUtil.createNormalniyIdFromUserBleat(userId)).get();
        model.addAttribute("currentUser", user);
        setRole(user, Role.MANAGER, manager);
        setRole(user, Role.ADMIN, admin);
        userRepo.save(user);
        return "redirect:/profile/" + user.getId();
    }

    private void setRole(User user, Role role, String check) {
        if (check != null && check.equals("yes")) {
            user.getRoles().add(role);
        } else {
            user.getRoles().remove(role);
        }
    }
}
