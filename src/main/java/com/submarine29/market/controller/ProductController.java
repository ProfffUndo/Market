package com.submarine29.market.controller;

import com.submarine29.market.domain.Category;
import com.submarine29.market.domain.Product;
import com.submarine29.market.dto.DataForFindDTO;
import com.submarine29.market.services.ValidationService;
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
import java.nio.charset.StandardCharsets;
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
        String str = "";
        if (from.isPresent() && to.isPresent() && from.get() > to.get()){
            model.addAttribute("priceError", "Цена от должна быть меньше цены до");
        }

        if (sort.isPresent()) {
            switch (sort.get()) {
                case ("priceup"):
                    pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),Sort.by("price").ascending());
                    break;
                case ("pricedown"):
                    pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),Sort.by("price").descending());
                    break;

                case ("popularityup"):
                    pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),Sort.by("popularity").ascending());
                    break;
                case ("popularitydown"):
                    pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),Sort.by("popularity").descending());
                    break;

                default:
                    pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),Sort.by("id").descending());
                    break;
            }
        }
        Category category = null;
        if (pageable.getPageSize() > 50 || pageable.getPageSize() < 1) {
            return "redirect:/products/?page=0&size=50";
        } else {
            if (categoryId.isEmpty() && name.isEmpty()) {
                model.addAttribute("productsPage", productRepo
                        .findAllFromToPrice(pageable, from.orElse(0.00), to.orElse(Double.MAX_VALUE)));
            }
            else if (categoryId.isPresent() && name.isEmpty()){
                category = categoryRepo.findById(categoryId.get()).get();
                model.addAttribute("productsPage", productRepo
                        .findAllFromToPriceAndCategory(pageable, from.orElse(0.00), to.orElse(Double.MAX_VALUE), categoryId.get()));
                //model.addAttribute("categoryOld", category.getName());
            }
            else if (categoryId.isEmpty()){
                str = new String(name.get().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
                model.addAttribute("productsPage", productRepo
                        .findAllFromToPriceAndSearch(pageable, from.orElse(0.00), to.orElse(Double.MAX_VALUE), str));
            }
            else {
                category = categoryRepo.findById(categoryId.get()).get();
                str = new String(name.get().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
                model.addAttribute("productsPage", productRepo
                        .findAllFromToPriceAndCategoryAndSearch(pageable, from.orElse(0.00), to.orElse(Double.MAX_VALUE), categoryId.get(), str));
            }
        }
        DataForFindDTO dto = new DataForFindDTO(str, from.orElse(null), to.orElse(null), categoryId.orElse(null), sort.orElse(null), category);
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
            category = new String(category.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            categoryId = categoryRepo.findByName(category).getId();
        }
        if (!Objects.equals(priceFrom, "")){
            priceFromDouble = Double.parseDouble(priceFrom.replaceAll(" ",""));
        }
        if (!Objects.equals(priceTo, "")){
            priceToDouble = Double.parseDouble(priceTo.replaceAll(" ",""));
        }

        DataForFindDTO data = new DataForFindDTO(name, priceFromDouble
                , priceToDouble, categoryId, sort, null);
        String nameFromDTO = data.getName();

        return "redirect:/products?" + data;
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
        if (!user.isManager()) {
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
        if (!user.isManager()) {
            return "error/error";
        }
        categoryName = new String(categoryName.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        product.changeUTF();
        return validationService.createUpsertErrorModel("products/new", categoryName, product, bindingResult, model,true);
    }

    @GetMapping("{id}/edit")
    public String updateProduct(@AuthenticationPrincipal User user,
                                Model model, @PathVariable("id") Product product) {
        if (!user.isManager()) {
            return "error/error";
        }
        model.addAttribute("product", product);
        String oldPrice = product.getPrice() + "";
        model.addAttribute("priceOld", oldPrice.replace(',', '.'));
        model.addAttribute("categories", categoryRepo.findAll());
        model.addAttribute("categoryOld",product.getCategory().getName());
        return "products/edit";
    }

    @PostMapping("{id}/edit")
    public String update(@AuthenticationPrincipal User user, 
                         @ModelAttribute("categoryName") String categoryName,
                         @Valid Product product,
                         BindingResult bindingResult,
                         Model model) {
        if (!user.isManager()) {
            return "error/error";
        }
        categoryName = new String(categoryName.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        product.changeUTF();
        return validationService.createUpsertErrorModel("products/edit", categoryName, product, bindingResult, model,false);
    }

    @PostMapping("{id}/delete")
    public String delete(@AuthenticationPrincipal User user,
                         @PathVariable("id") Product product) {
        if (!user.isManager()) {
            return "error/error";
        }
        productRepo.delete(product);
        return "redirect:/products";
    }
}
