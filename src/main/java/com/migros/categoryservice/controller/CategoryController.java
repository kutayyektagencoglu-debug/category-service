package com.migros.categoryservice.controller;

import com.migros.categoryservice.dto.CategoryRequestDTO;
import com.migros.categoryservice.dto.CategoryResponseDTO;
import com.migros.categoryservice.enums.CategoryType;
import com.migros.categoryservice.enums.UnitType;
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
    public CategoryResponseDTO createCategory(@RequestBody CategoryRequestDTO dto) {

        return categoryService.createCategory(dto);
    }

    // READ ALL
    @GetMapping
    public List<CategoryResponseDTO> getAllCategories() {

        return categoryService.getAllCategories();
    }
    //READ BY ID
    @GetMapping("/{id}")
    public  CategoryResponseDTO getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }
    // READ BY NAME
    @GetMapping("/{name}")
    public CategoryResponseDTO getCategoryByName(@PathVariable String name) {

        return categoryService.getCategoryByName(name);
    }

    //READ BY CODE
    @GetMapping("/{code}")
    public CategoryResponseDTO getCategoryByCode(@PathVariable String code){

        return categoryService.getCategoryByCode(code);
    }

    //READ BY UNIT
    @GetMapping("/{unit}")
    public List<CategoryResponseDTO> getCategoryByUnit(@PathVariable UnitType unit){

        return categoryService.getCategoryByUnit(unit);
    }

    //READ BY CATEGORY CODE
    @GetMapping("/{categoryCode}")
    public List<CategoryResponseDTO> getCategoryByCategoryCode(@PathVariable CategoryType categoryCode){

        return categoryService.getCategoryByCategoryCode(categoryCode);
    }

    //READ BY BRAND
    @GetMapping("/{brand}")
    public List<CategoryResponseDTO> getCategoryByBrand(@PathVariable String brand){

        return categoryService.getCategoryByBrand(brand);
    }

    // UPDATE
    @PutMapping("/{name}")
    public CategoryResponseDTO updateCategory(@PathVariable String name, @RequestBody CategoryRequestDTO updatedDTO) {

        return categoryService.updateCategory(name, updatedDTO);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deleteCategoryById(@PathVariable Long id) {

        categoryService.deleteCategoryById(id);
    }

    @DeleteMapping("/{name}")
    public void deleteCategoryByName(@PathVariable String name) {

        categoryService.deleteCategoryByName(name);
    }

    @DeleteMapping("/{code}")
    public void deleteCategoryByCode(@PathVariable String code) {

        categoryService.deleteCategoryByCode(code);
    }
}

