package com.migros.categoryservice.service;

import com.migros.categoryservice.dto.CategoryRequestDTO;
import com.migros.categoryservice.dto.CategoryResponseDTO;
import com.migros.categoryservice.mapper.CategoryMapper;
import com.migros.categoryservice.model.Category;
import com.migros.categoryservice.repository.CategoryRepository;
import com.migros.commonerror.exception.BusinessException;
import jakarta.transaction.Transactional;
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
            throw new BusinessException("INVALID_CATEGORY_CODE", "Code has to be 2 letters", 400);
        }
        Category category = mapper.toEntity(dto);
        if(categoryRepository.existsByName(category.getName())) {
            throw new BusinessException("CATEGORY_NAME_EXISTS", "Category name already exists", 409);
        }

        String code = category.getCode().toUpperCase();
        if(categoryRepository.existsByCode(code)) {
            throw new BusinessException("CATEGORY_CODE_EXISTS", "Category code already exists", 409);
        }
        category.setCode(code);
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
                .orElseThrow(() -> new BusinessException("CATEGORY_NOT_FOUND", "Category not found: " + id, 404));
        return mapper.toResponseDTO(category);
    }
    //READ BY NAME
    public CategoryResponseDTO getCategoryByName(String name) {
        Category category = categoryRepository.findByName(name)
                .orElseThrow(() -> new BusinessException("CATEGORY_NOT_FOUND", "Category not found: " + name, 404));
        return mapper.toResponseDTO(category);
    }

    //READ BY CODE
    public CategoryResponseDTO getCategoryByCode(String code){
        Category category = categoryRepository.findByCode(code)
                .orElseThrow(() -> new BusinessException("CATEGORY_NOT_FOUND", "Category not found: " + code, 404));
        return mapper.toResponseDTO(category);
    }

    //VERIFY
    public boolean verifyCategoryCode(String code) {
        return categoryRepository.existsByCode(code);
    }

    //UPDATE CATEGORY NAME
    /*
    @Transactional
    public CategoryResponseDTO updateCategoryByName(String name, CategoryRequestDTO desiredDTO) {
        Category existingCategory = categoryRepository.findByName(name)
                .orElseThrow(() -> new BusinessException("CATEGORY_NOT_FOUND", "Category not found: " + name, 404));

        existingCategory.setName(desiredDTO.getName());

        Category saved = categoryRepository.save(existingCategory);
        return mapper.toResponseDTO(saved);
    }
     */
    @Transactional
    public CategoryResponseDTO updateCategoryByCode(String code, CategoryRequestDTO desiredDTO) {
        Category existingCategory = categoryRepository.findByCode(code)
                .orElseThrow(() -> new BusinessException("CATEGORY_NOT_FOUND", "Category not found: " + code, 404));

        if(!desiredDTO.getCode().equals(existingCategory.getCode())) {
            throw new BusinessException("WRONG_CATEGORY_CODE", "You can't change category code", 400);
        }
        existingCategory.setName(desiredDTO.getName());

        Category saved = categoryRepository.save(existingCategory);
        return mapper.toResponseDTO(saved);
    }

    //DELETE
    /*
    @Transactional
    public void deleteCategoryByCode(String code) {
        if(!categoryRepository.existsByCode(code)) {
            throw new IllegalArgumentException("Category not found: " + code);
        }
        //check whether category has any products before deleting
        categoryRepository.deleteByCode(code);
    }
    */
}
