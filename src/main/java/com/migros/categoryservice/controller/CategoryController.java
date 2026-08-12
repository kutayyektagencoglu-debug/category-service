package com.migros.categoryservice.controller;

import com.migros.categoryservice.dto.CategoryRequestDTO;
import com.migros.categoryservice.dto.CategoryResponseDTO;
import com.migros.categoryservice.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {

        this.categoryService = categoryService;
    }

    //CREATE
    @PostMapping
    public CategoryResponseDTO createCategory(@Valid @RequestBody CategoryRequestDTO dto) {

        return categoryService.createCategory(dto);
    }

    //READ ALL
    @GetMapping
    public List<CategoryResponseDTO> getAllCategories() {

        return categoryService.getAllCategories();
    }
    //READ BY ID
    @GetMapping("/id/{id}")
    public  CategoryResponseDTO getCategoryById(@PathVariable Long id) {

        return categoryService.getCategoryById(id);
    }
    //READ BY NAME
    @GetMapping("/name/{name}")
    public CategoryResponseDTO getCategoryByName(@PathVariable String name) {

        return categoryService.getCategoryByName(name);
    }

    //READ BY CODE
    @GetMapping("/code/{code}")
    public CategoryResponseDTO getCategoryByCode(@PathVariable String code){

        return categoryService.getCategoryByCode(code);
    }

    //VERIFY
    @GetMapping("/verify/{code}")
    public boolean verifyCategoryCode(@PathVariable String code) {

        return categoryService.verifyCategoryCode(code);
    }

    //UPDATE
    @PutMapping("/name/{name}")
    public CategoryResponseDTO updateCategoryByName(@PathVariable String name, @Valid @RequestBody CategoryRequestDTO updatedDTO) {

        return categoryService.updateCategoryByName(name, updatedDTO);
    }

    @PutMapping("/code/{code}")
    public CategoryResponseDTO updateCategoryByCode(@PathVariable String code, @Valid @RequestBody CategoryRequestDTO updatedDTO) {

        return categoryService.updateCategoryByCode(code, updatedDTO);
    }

    //DELETE
    /*
    @DeleteMapping("/name/{name}")
    public void deleteCategoryByName(@PathVariable String name) {

        categoryService.deleteCategoryByName(name);
    }

    @DeleteMapping("/code/{code}")
    public void deleteCategoryByCode(@PathVariable String code) {

        categoryService.deleteCategoryByCode(code);
    }
    */
}

