package com.submarine29.market.controller;

import com.submarine29.market.domain.Category;
import com.submarine29.market.domain.Product;
import com.submarine29.market.repo.CategoryRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryRepo categoryRepo;

    @Autowired
    public CategoryController(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @GetMapping
    public List<Category> list() {
        return categoryRepo.findAll();
    }

    @GetMapping("{id}")
    public Category getOne(@PathVariable("id") Category category) {
        return category;
    }

    @PostMapping
    public Category create(@RequestBody Category category) {
        return categoryRepo.save(category);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Category category) {
        categoryRepo.delete(category);
    }

    @PutMapping("{id}")
    public Category update(@PathVariable("id") Category categoryFromDB, @RequestBody Category categoryNew) {
        BeanUtils.copyProperties(categoryNew, categoryFromDB, "id");
        return categoryRepo.save(categoryFromDB);
    }
}
