package com.migros.categoryservice.controller;

import com.migros.categoryservice.dto.CategoryDTO;
import com.migros.categoryservice.enums.CategoryType;
import com.migros.categoryservice.enums.UnitType;
import com.migros.categoryservice.model.Category;
import com.migros.categoryservice.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category-service")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {

        this.categoryService = categoryService;
    }

    // CREATE
    @PostMapping
    public CategoryDTO createCategory(@RequestBody CategoryDTO dto) {

        return categoryService.createCategory(dto);
    }

    // READ ALL
    @GetMapping
    public List<CategoryDTO> getAllCategories() {

        return categoryService.getAllCategories();
    }

    // READ BY NAME
    @GetMapping("/{name}")
    public CategoryDTO getCategoryByName(@PathVariable String name) {

        return categoryService.getCategoryByName(name);
    }

    //READ BY CODE
    @GetMapping("/{code}")
    public CategoryDTO getCategoryByCode(@PathVariable String code){

        return categoryService.getCategoryByCode(code);
    }

    //READ BY UNIT
    @GetMapping("/{unit}")
    public List<CategoryDTO> getCategoryByUnit(@PathVariable UnitType unit){

        return categoryService.getCategoryByUnit(unit);
    }

    //READ BY CATEGORY CODE
    @GetMapping("/{categoryCode}")
    public List<CategoryDTO> getCategoryByCategoryCode(@PathVariable CategoryType categoryCode){

        return categoryService.getCategoryByCategoryCode(categoryCode);
    }

    //READ BY BRAND
    @GetMapping("/{brand}")
    public List<CategoryDTO> getCategoryByBrand(@PathVariable String brand){

        return categoryService.getCategoryByBrand(brand);
    }

    // UPDATE
    @PutMapping("/{name}")
    public CategoryDTO updateCategory(@PathVariable String name, @RequestBody CategoryDTO updatedDTO) {

        return categoryService.updateCategory(name, updatedDTO);
    }

    // DELETE
    @DeleteMapping("/{name}")
    public void deleteCategory(@PathVariable String name) {

        categoryService.deleteCategory(name);
    }
}

