package com.migros.categoryservice.service;

import com.migros.categoryservice.dto.CategoryRequestDTO;
import com.migros.categoryservice.dto.CategoryResponseDTO;
import com.migros.categoryservice.mapper.CategoryMapper;
import com.migros.categoryservice.model.Category;
import com.migros.categoryservice.repository.CategoryRepository;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

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
        if(dto.getCode().length() != 2) {
            throw new IllegalArgumentException("Invalid code (Code has to be 2 letters");
        }
        Category category = mapper.toEntity(dto);
        if(categoryRepository.existsByName(category.getName())) {
            throw new IllegalArgumentException("Category name already exists");
        }
        if(categoryRepository.existsByCode(category.getCode())) {
            throw new IllegalArgumentException("Category code already exists");
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

    //VERIFY
    public boolean verifyCategoryCode(String code) {
        return categoryRepository.existsByCode(code);
    }

    //UPDATE CATEGORY NAME
    public CategoryResponseDTO updateCategoryById(Long id, CategoryRequestDTO desiredDTO) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found: " + id));

        existingCategory.setName(desiredDTO.getName());

        Category saved = categoryRepository.save(existingCategory);
        return mapper.toResponseDTO(saved);
    }

    public CategoryResponseDTO updateCategoryByName(String name, CategoryRequestDTO desiredDTO) {
        Category existingCategory = categoryRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("Category not found: " + name));

        existingCategory.setName(desiredDTO.getName());

        Category saved = categoryRepository.save(existingCategory);
        return mapper.toResponseDTO(saved);
    }

    public CategoryResponseDTO updateCategoryByCode(String code, CategoryRequestDTO desiredDTO) {
        Category existingCategory = categoryRepository.findByCode(code)
                .orElseThrow(() -> new IllegalArgumentException("Category not found: " + code));

        existingCategory.setName(desiredDTO.getName());

        Category saved = categoryRepository.save(existingCategory);
        return mapper.toResponseDTO(saved);
    }

    //DELETE
    public void deleteCategoryById(Long id) {
        if(!categoryRepository.existsById(id)) {
            throw new IllegalArgumentException("Category not found: " + id);
        }
        //check whether category has any products before deleting
        categoryRepository.deleteById(id);
    }

    public void deleteCategoryByName(String name) {
        if(!categoryRepository.existsByName(name)) {
            throw new IllegalArgumentException("Category not found: " + name);
        }
        //check whether category has any products before deleting
        categoryRepository.deleteByName(name);
    }

    public void deleteCategoryByCode(String code) {
        if(!categoryRepository.existsByCode(code)) {
            throw new IllegalArgumentException("Category not found: " + code);
        }
        //check whether category has any products before deleting
        categoryRepository.deleteByCode(code);
    }
}
