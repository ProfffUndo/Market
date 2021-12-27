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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ValidationService {
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ProductRepo productRepo;

    public String createUpsertErrorModel(String errorUrl, String categoryName,
                                          MultipartFile image,
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

       // String imagePath;
        if (!image.isEmpty())
        {
            try {
                //String orgName = image.getOriginalFilename();
                //String name=orgName.substring(orgName.lastIndexOf("\\")+1,orgName.length());
                String imagePath="product_"+product.getId();
                File file1= File.createTempFile(imagePath,".jpg");
                image.transferTo(file1);
                product.setImagePath(file1.getName());
            } catch (IOException e) {
                return errorUrl;
            }
        }

        product.setCategory(category);
        productRepo.save(product);
        model.addAttribute("product", product);
        return "/products/show";
    }

}
