package com.submarine29.market.controller;

import com.submarine29.market.domain.Product;
import com.submarine29.market.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@Controller
@RequestMapping("/")
public class MainController {

    private final ProductRepo productRepo;

    @Autowired
    public MainController(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @GetMapping
    public String main(Model model) {
        return "main";
    }

    @GetMapping("/products")
    public String products(Model model) {
//        model.addAttribute("products", productRepo.findAll());
        model.addAttribute("products", productRepo.findAll());
        return "products/list";
    }

    @GetMapping("/products/new")
    public String newProduct() {
        return "products/new";
    }

    @GetMapping("/products/{id}")
    public String showProduct(
            @PathVariable Long id, Model model) {
        model.addAttribute("product", productRepo.findById(id));
        return "products/show";
    }

    @GetMapping("/basket")
    public String basket(Model model) {
        return "basket/basket";
    }
}
