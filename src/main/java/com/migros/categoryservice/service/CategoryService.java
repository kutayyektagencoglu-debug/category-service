package com.migros.categoryservice.service;

import com.migros.categoryservice.dto.CategoryDTO;
import com.migros.categoryservice.enums.CategoryType;
import com.migros.categoryservice.enums.UnitType;
import com.migros.categoryservice.mapper.CategoryMapper;
import com.migros.categoryservice.model.Category;
import com.migros.categoryservice.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper mapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper mapper) {
        this.mapper = mapper;
        this.categoryRepository = categoryRepository;
    }

    //CREATE
    public CategoryDTO createCategory(CategoryDTO dto) {
        Category category = mapper.toEntity(dto);
        if(categoryRepository.existsByName(category.getName())) {
            throw new IllegalArgumentException("Category name already exists");
        }
        Category saved = categoryRepository.save(category);
        return mapper.toDTO(saved);
    }

    //READ ALL
    public List<CategoryDTO> getAllCategories(){
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDTO> dtoList = new ArrayList<>();
        for(Category category: categories) {
            dtoList.add(mapper.toDTO(category));
        }
        return dtoList;
    }

    //READ BY NAME
    public CategoryDTO getCategoryByName(String name) {
        Category category = categoryRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("Category not found: " + name));
        return mapper.toDTO(category);
    }

    //READ BY CODE
    public CategoryDTO getCategoryByCode(String code){
        Category category = categoryRepository.findByCode(code)
                .orElseThrow(() ->  new IllegalArgumentException("Category not found: " + code));
        return mapper.toDTO(category);
    }

    //READ BY UNIT
    public List<CategoryDTO> getCategoryByUnit(UnitType unit){
        List<Category> categories = categoryRepository.findByUnit(unit);
        List<CategoryDTO> dtoList = new ArrayList<>();
        for(Category category: categories) {
            dtoList.add(mapper.toDTO(category));
        }
        return dtoList;
    }

    //READ BY CATEGORY CODE
    public List<CategoryDTO> getCategoryByCategoryCode(CategoryType categoryCode){
        List<Category> categories = categoryRepository.findByCategoryCode(categoryCode);
        List<CategoryDTO> dtoList = new ArrayList<>();
        for(Category category: categories) {
            dtoList.add(mapper.toDTO(category));
        }
        return dtoList;
    }

    //READ BY BRAND
    public List<CategoryDTO> getCategoryByBrand(String brand){
        List<Category> categories = categoryRepository.findByBrand(brand);
        List<CategoryDTO> dtoList = new ArrayList<>();
        for(Category category: categories) {
            dtoList.add(mapper.toDTO(category));
        }
        return dtoList;
    }

    //UPDATE
    public CategoryDTO updateCategory(String name, CategoryDTO desiredDTO) {
        Category existingCategory = categoryRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("Category not found: " + name));

        existingCategory.setCode(desiredDTO.getCode());
        existingCategory.setUnit(desiredDTO.getUnit());
        existingCategory.setBrand(desiredDTO.getBrand());
        existingCategory.setCategoryCode(desiredDTO.getCategoryCode());

        Category saved = categoryRepository.save(existingCategory);
        return mapper.toDTO(saved);
    }

    //DELETE
    public void deleteCategory(String name) {
        if(!categoryRepository.existsByName(name)) {
            throw new IllegalArgumentException("Category not found: " + name);
        }
        categoryRepository.deleteById(name);
    }
}
