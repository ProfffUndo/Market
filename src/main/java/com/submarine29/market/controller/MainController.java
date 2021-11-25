package com.submarine29.market.controller;

import com.submarine29.market.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String products(
            Model model,
            @PageableDefault(sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable
    ) {
//      model.addAttribute("products", productRepo.findAll());
        model.addAttribute("productsPage", productRepo.findAll(pageable));
        model.addAttribute("url","/products");
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
