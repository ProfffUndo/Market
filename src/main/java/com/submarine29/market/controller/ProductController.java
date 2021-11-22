package com.submarine29.market.controller;

import com.submarine29.market.domain.Category;
import com.submarine29.market.domain.Product;
import com.submarine29.market.repo.ProductRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductRepo productRepo;

    @Autowired
    public ProductController(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @GetMapping("{id}")
    public Product getOne(@PathVariable("id") Product product) {
        return product;
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        return productRepo.save(product);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Product product) {
        productRepo.delete(product);
    }

    @PutMapping("{id}")
    public Product update(@PathVariable("id") Product productFromDB, @RequestBody Product productNew) {
        BeanUtils.copyProperties(productNew, productFromDB, "id");
        return productRepo.save(productFromDB);
    }
}
