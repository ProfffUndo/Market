package com.submarine29.market.controller;

import com.submarine29.market.domain.Category;
import com.submarine29.market.domain.Product;
import com.submarine29.market.domain.Role;
import com.submarine29.market.domain.User;
import com.submarine29.market.repo.CategoryRepo;
import com.submarine29.market.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;

    @Autowired
    public ProductController(ProductRepo productRepo, CategoryRepo categoryRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }

    @GetMapping
    public String products(
            Model model,
            @PageableDefault(sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable
    ) {
        if (pageable.getPageSize() > 50 || pageable.getPageSize() < 1) {
            return "redirect:/products/?page=0&size=50";
        } else {
            model.addAttribute("productsPage", productRepo.findAll(pageable));
            model.addAttribute("url", "/products");
        }
        return "products/list";
    }

    @GetMapping("{id}")
    public String showProduct(
            @PathVariable("id") Product product, Model model) {
        model.addAttribute("product", product);
        return "products/show";
    }

    @GetMapping("new")
    public String newProduct(@AuthenticationPrincipal User user,
                             Model model) {
        if (!user.getAuthorities().contains(Role.MANAGER)) {
            return "error/error";
        }
        model.addAttribute("categories", categoryRepo.findAll());
        return "products/new";
    }

    @PostMapping("/new")
    public String create(@AuthenticationPrincipal User user,
                         @ModelAttribute("categoryName") String categoryName,
                         @Valid Product product,
                         BindingResult bindingResult,
                         Model model) {
        if (!user.getAuthorities().contains(Role.MANAGER)) {
            return "error/error";
        }
        return createUpsertErrorModel("products/new", categoryName, product, bindingResult, model);
    }

    @GetMapping("{id}/edit")
    public String updateProduct(@AuthenticationPrincipal User user,
                                Model model, @PathVariable("id") Product product) {
        if (!user.getAuthorities().contains(Role.MANAGER)) {
            return "error/error";
        }
        model.addAttribute("product", product);
        String oldPrice = product.getPrice() + "";
        model.addAttribute("priceOld", oldPrice.replace(',', '.'));
        model.addAttribute("categories", categoryRepo.findAll());
        return "products/edit";
    }

    @PostMapping("{id}/edit")
    public String update(@AuthenticationPrincipal User user, 
                         @ModelAttribute("categoryName") String categoryName,
                         @Valid Product product,
                         BindingResult bindingResult,
                         Model model) {
        if (!user.getAuthorities().contains(Role.MANAGER)) {
            return "error/error";
        }
        return createUpsertErrorModel("products/edit", categoryName, product, bindingResult, model);
    }

    @PostMapping("{id}/delete")
    public String delete(@AuthenticationPrincipal User user,
                         @PathVariable("id") Product product) {
        if (!user.getAuthorities().contains(Role.MANAGER)) {
            return "error/error";
        }
        productRepo.delete(product);
        return "redirect:/products";
    }

    private String createUpsertErrorModel(String errorUrl, String categoryName,
                                          Product product,
                                          BindingResult bindingResult,
                                          Model model) {
        Map<String, String> errorsMap = new HashMap<>();
        Category category = categoryRepo.findByName(categoryName);
        if (category == null) {
            errorsMap.put("categoryError", "Необходимо выбрать категорию товара!");
        }

        if (category == null || bindingResult.hasErrors()) {
            String priceOld = product.getPrice() + "";
            model.addAttribute("priceOld", priceOld.replace(",", "."));
            model.addAttribute("categories", categoryRepo.findAll());
            model.addAttribute("categoryOld", categoryName);
            errorsMap.putAll(ControllerUtil.getErrors(bindingResult));
            model.mergeAttributes(errorsMap);
            return errorUrl;
        }
        product.setCategory(category);
        productRepo.save(product);
        model.addAttribute("product", product);
        return "/products/show";
    }

}
