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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;
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
            @RequestParam("category_id") Optional<Long> categoryId,
            @RequestParam("sort") Optional<String> sort,
            @RequestParam("name") Optional<String> name
            ) {
        //String category = null;
        if (sort.isPresent()) {
            switch (sort.get()) {
                case ("priceup"):
                    pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),Sort.by("price").ascending());
                    break;
                case ("pricedown"):
                    pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),Sort.by("price").descending());
                    break;

                default:
                    pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),Sort.by("id").descending());
                    break;
            }
        }
        if (pageable.getPageSize() > 50 || pageable.getPageSize() < 1) {
            return "redirect:/products/?page=0&size=50";
        } else {
            if (categoryId.isEmpty()) {
                model.addAttribute("productsPage", productRepo
                        .findAllFromToPrice(pageable, from.orElse(0.00), to.orElse(Double.MAX_VALUE)));
            }
            else{
                //category = categoryRepo.findById(categoryId.get()).get().getName();
                model.addAttribute("productsPage", productRepo
                        .findAllFromToPriceAndCategory(pageable, from.orElse(0.00), to.orElse(Double.MAX_VALUE), categoryId.get()));
                //model.addAttribute("categoryOld", category.getName());
            }
        }
        DataForFindDTO dto = new DataForFindDTO("", from.orElse(null), to.orElse(null), categoryId.orElse(null), sort.orElse(null));
        model.addAttribute("categories", categoryRepo.findAll());
        model.addAttribute("dto", dto);
        String params = "";
        String paramsForSort = "";
        if (!dto.toString().equals("")) {
            params = dto.toString();
            paramsForSort = dto.getUrlForSort();
        }
        model.addAttribute("url", "/products?" + params);
        model.addAttribute("urlForSort", "/products?" + paramsForSort);
        return "products/list";
    }

    @PostMapping("find/find/find")
    public String productsFind(@ModelAttribute("name") String name,
                               @ModelAttribute("priceFrom") String priceFrom,
                               @ModelAttribute("priceTo") String priceTo,
                               @ModelAttribute("category") String category,
                               @ModelAttribute("sort") String sort) {
        Long categoryId = null;
        Double priceFromDouble = null;
        Double priceToDouble = null;
        //categoryId = categoryRepo.findByName(category).getId();
        if (!Objects.equals(category, "")){
            categoryId = categoryRepo.findByName(category).getId();
        }
        if (!Objects.equals(priceFrom, "")){
            priceFromDouble = Double.parseDouble(priceFrom);
        }
        if (!Objects.equals(priceTo, "")){
            priceToDouble = Double.parseDouble(priceTo);
        }
        if (priceFromDouble != null && priceToDouble != null && priceFromDouble > priceToDouble){
            return "error/error"; //временно
        }

        return "redirect:/products?" + new DataForFindDTO(name, priceFromDouble
                , priceToDouble, categoryId, sort);
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
