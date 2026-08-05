package com.migros.categoryservice.service;

import com.migros.categoryservice.dto.CategoryRequestDTO;
import com.migros.categoryservice.dto.CategoryResponseDTO;
import com.migros.categoryservice.enums.CategoryType;
import com.migros.categoryservice.enums.UnitType;
import com.migros.categoryservice.mapper.CategoryMapper;
import com.migros.categoryservice.model.Category;
import com.migros.categoryservice.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper mapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper mapper) {
        this.mapper = mapper;
        this.categoryRepository = categoryRepository;
    }

    //CREATE
    public CategoryResponseDTO createCategory(CategoryRequestDTO dto) {
        Category category = mapper.toEntity(dto);
        if(categoryRepository.existsByName(category.getName())) {
            throw new IllegalArgumentException("Category name already exists");
        }
        Category saved = categoryRepository.save(category);
        return mapper.toResponseDTO(saved);
    }

    //READ ALL
    public List<CategoryResponseDTO> getAllCategories(){
        List<Category> categories = categoryRepository.findAll();
        return mapper.toResponseDTOList(categories);
    }
    //READ BY ID
    public CategoryResponseDTO getCategoryById(Long id) {
        Category category= categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found: " + id));
        return mapper.toResponseDTO(category);
    }
    //READ BY NAME
    public CategoryResponseDTO getCategoryByName(String name) {
        Category category = categoryRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("Category not found: " + name));
        return mapper.toResponseDTO(category);
    }

    //READ BY CODE
    public CategoryResponseDTO getCategoryByCode(String code){
        Category category = categoryRepository.findByCode(code)
                .orElseThrow(() ->  new IllegalArgumentException("Category not found: " + code));
        return mapper.toResponseDTO(category);
    }

    //READ BY UNIT
    public List<CategoryResponseDTO> getCategoryByUnit(UnitType unit){
        List<Category> categories = categoryRepository.findByUnit(unit);
        return mapper.toResponseDTOList(categories);
    }

    //READ BY CATEGORY CODE
    public List<CategoryResponseDTO> getCategoryByCategoryCode(CategoryType categoryCode){
        List<Category> categories = categoryRepository.findByCategoryCode(categoryCode);
        return mapper.toResponseDTOList(categories);
    }

    //READ BY BRAND
    public List<CategoryResponseDTO> getCategoryByBrand(String brand){
        List<Category> categories = categoryRepository.findByBrand(brand);
        return mapper.toResponseDTOList(categories);
    }

    //UPDATE
    public CategoryResponseDTO updateCategory(String name, CategoryRequestDTO desiredDTO) {
        Category existingCategory = categoryRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("Category not found: " + name));
        existingCategory.setName(desiredDTO.getName());
        existingCategory.setCode(desiredDTO.getCode());
        existingCategory.setUnit(desiredDTO.getUnit());
        existingCategory.setBrand(desiredDTO.getBrand());
        existingCategory.setCategoryCode(desiredDTO.getCategoryCode());

        Category saved = categoryRepository.save(existingCategory);
        return mapper.toResponseDTO(saved);
    }

    //DELETE
    public void deleteCategoryById(Long id) {
        if(!categoryRepository.existsById(id)) {
            throw new IllegalArgumentException("Category not found: " + id);
        }
        categoryRepository.deleteById(id);
    }

    public void deleteCategoryByName(String name) {
        if(!categoryRepository.existsByName(name)) {
            throw new IllegalArgumentException("Category not found: " + name);
        }
        categoryRepository.deleteByName(name);
    }

    public void deleteCategoryByCode(String code) {
        if(!categoryRepository.existsByCode(code)) {
            throw new IllegalArgumentException("Category not found: " + code);
        }
        categoryRepository.deleteByCode(code);
    }
}
