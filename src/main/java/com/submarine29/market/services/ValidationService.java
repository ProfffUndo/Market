package com.submarine29.market.services;

import com.submarine29.market.controller.ControllerUtil;
import com.submarine29.market.domain.Category;
import com.submarine29.market.domain.Product;
import com.submarine29.market.repo.CategoryRepo;
import com.submarine29.market.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;

@Service
public class ValidationService {
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ProductRepo productRepo;

    public String createUpsertErrorModel(String errorUrl, String categoryName,
                                          Product product,
                                          BindingResult bindingResult,
                                          Model model, boolean isNew) {
        Map<String, String> errorsMap = new HashMap<>();
        Category category = categoryRepo.findByName(categoryName);
        if (category == null) {
            errorsMap.put("categoryError", "Необходимо выбрать категорию товара!");
        }

        if (isNew && productRepo.findByName(product.getName()) != null){
            errorsMap.put("productNameError", "Товар с таким названием уже существует!");
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
