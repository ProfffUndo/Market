package com.submarine29.market.controller;

import com.submarine29.market.domain.Category;
import com.submarine29.market.domain.Product;
import com.submarine29.market.dto.DataForFindDTO;
import com.submarine29.market.services.ValidationService;
import com.submarine29.market.domain.Role;
import com.submarine29.market.domain.User;
import com.submarine29.market.repo.CategoryRepo;
import com.submarine29.market.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;
    private final ValidationService validationService;

    @Autowired
    public ProductController(ProductRepo productRepo, CategoryRepo categoryRepo, ValidationService validationService) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
        this.validationService = validationService;
    }

    @GetMapping
    public String products(
            Model model,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
            @RequestParam("from") Optional<Double> from, @RequestParam("to") Optional<Double> to,
            @RequestParam("category_id") Optional<Long> categoryId
            ) {
        Long category = null;
        if (pageable.getPageSize() > 50 || pageable.getPageSize() < 1) {
            return "redirect:/products/?page=0&size=50";
        } else {
            if (categoryId.isEmpty()) {
                model.addAttribute("productsPage", productRepo
                        .findAllFromToPrice(pageable, from.orElse(0.00), to.orElse(Double.MAX_VALUE)));
            }
            else{
                category = categoryRepo.findById(categoryId.get()).get().getId();
                model.addAttribute("productsPage", productRepo
                        .findAllFromToPriceAndCategory(pageable, from.orElse(0.00), to.orElse(Double.MAX_VALUE), categoryId.get()));
                //model.addAttribute("categoryOld", category.getName());
            }
        }
        DataForFindDTO dto = new DataForFindDTO("", from.orElse(null), to.orElse(null), category);
        model.addAttribute("categories", categoryRepo.findAll());
        model.addAttribute("dto", dto);
        String params = "";
        if (!dto.toString().equals("")) {
            params = dto.toString() + "&";
        }
        model.addAttribute("url", "/products?" + params);
        return "products/list";
    }

    @PostMapping("find/find/find")
    public String productsFind(@ModelAttribute("name") String name,
                               @ModelAttribute("priceFrom") String priceFrom,
                               @ModelAttribute("priceTo") String priceTo,
                               @ModelAttribute("category") String category) {
        return "redirect:/products?" + new DataForFindDTO(name, Double.parseDouble(priceFrom)
                , Double.parseDouble(priceTo), categoryRepo.findByName(category).getId());
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
        model.addAttribute("priceOld","0.0");
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
        return validationService.createUpsertErrorModel("products/new", categoryName, product, bindingResult, model);
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
        return validationService.createUpsertErrorModel("products/edit", categoryName, product, bindingResult, model);
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
}
