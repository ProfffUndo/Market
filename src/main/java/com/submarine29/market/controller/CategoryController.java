package com.submarine29.market.controller;

import com.submarine29.market.domain.Category;
import com.submarine29.market.domain.User;
import com.submarine29.market.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/category")
public class CategoryController {
    private final CategoryRepo categoryRepo;

    @Autowired
    public CategoryController(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @GetMapping("/new")
    public String list(@AuthenticationPrincipal User user) {
        if (!user.isManager()) {
            return "error/error";
        }
        return "category/new";
    }

    @PostMapping("new/new")
    public String create(@AuthenticationPrincipal User user,
                         @Valid Category category, BindingResult bindingResult,
                         Model model) {
        if (!user.isManager()) {
            return "error/error";
        }
        category.changeUTF();
        if (bindingResult.hasErrors()) {
            model.addAttribute("category", category);
            Map<String, String> errorsMap = new HashMap<>(ControllerUtil.getErrors(bindingResult));
            model.mergeAttributes(errorsMap);
            return "category/new";
        }
        categoryRepo.save(category);
        return "redirect:/products";
    }
}
