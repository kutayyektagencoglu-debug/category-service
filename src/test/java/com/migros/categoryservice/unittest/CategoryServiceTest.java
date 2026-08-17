package com.migros.categoryservice.unittest;

import com.migros.categoryservice.dto.CategoryRequestDTO;
import com.migros.categoryservice.dto.CategoryResponseDTO;
import com.migros.categoryservice.mapper.CategoryMapper;
import com.migros.categoryservice.model.Category;
import com.migros.categoryservice.repository.CategoryRepository;
import com.migros.categoryservice.service.CategoryService;
import com.migros.commonerror.exception.BusinessException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Category Service Unit Tests")
public class CategoryServiceTest {
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private CategoryMapper mapper;

    @InjectMocks
    private CategoryService categoryService;

    //Create
    @Test
    void createCategoryInvalidCode() {
        CategoryRequestDTO dto = new CategoryRequestDTO();
        dto.setCode("tes");

        assertThrows(BusinessException.class, () -> categoryService.createCategory(dto));
    }

    @Test
    void createCategoryDuplicateName() {
        CategoryRequestDTO dto = new CategoryRequestDTO();
        dto.setName("test");
        dto.setCode("TS");

        Category category = new Category();
        category.setName("test");
        category.setCode("TS");

        when(mapper.toEntity(dto)).thenReturn(category);
        when(categoryRepository.existsByName("test")).thenReturn(true);

        assertThrows(BusinessException.class, () -> categoryService.createCategory(dto));
    }

    @Test
    void createCategoryDuplicateCode() {
        CategoryRequestDTO dto = new CategoryRequestDTO();
        dto.setName("test");
        dto.setCode("TS");

        Category category = new Category();
        category.setName("test");
        category.setCode("TS");

        when(mapper.toEntity(dto)).thenReturn(category);
        when(categoryRepository.existsByName("test")).thenReturn(false);
        when(categoryRepository.existsByCode("TS")).thenReturn(true);

        assertThrows(BusinessException.class, () -> categoryService.createCategory(dto));
    }

    @Test
    void createCategorySuccess() {
        CategoryRequestDTO dto = new CategoryRequestDTO();
        dto.setName("test");
        dto.setCode("ts");

        Category category = new Category();
        category.setName("test");
        category.setCode("ts");

        when(mapper.toEntity(dto)).thenReturn(category);
        when(categoryRepository.existsByName("test")).thenReturn(false);
        when(categoryRepository.existsByCode("TS")).thenReturn(false);
        when(categoryRepository.save(any(Category.class))).thenReturn(category);
        when(mapper.toResponseDTO(category)).thenReturn(new CategoryResponseDTO());

        CategoryResponseDTO result = categoryService.createCategory(dto);

        assertNotNull(result);
        assertEquals("TS", category.getCode());
        verify(categoryRepository).save(category);
    }

    //Update
    @Test
    void updateCategoryByCodeNotFound() {
    String code = "TS";
    CategoryRequestDTO dto = new CategoryRequestDTO();

        when(categoryRepository.findByCode(code)).thenReturn(Optional.empty());

        assertThrows(BusinessException.class, () -> categoryService.updateCategoryByCode(code, dto));
    }

    @Test
    void updateCategoryDifferentCode() {
        String code = "TS";

        CategoryRequestDTO dto = new CategoryRequestDTO();
        dto.setName("new");
        dto.setCode("NE");

        Category category = new Category();
        category.setName("new");
        category.setCode(code);

        when(categoryRepository.findByCode("TS")).thenReturn(Optional.of(category));

        assertThrows(BusinessException.class, () -> categoryService.updateCategoryByCode(code, dto));

    }

    @Test
    void updateCategoryByCodeSuccess() {
        String code = "TS";

        CategoryRequestDTO dto = new CategoryRequestDTO();
        dto.setName("new");
        dto.setCode(code);

        Category category = new Category();
        category.setName("original");
        category.setCode(code);

        when(categoryRepository.findByCode(code)).thenReturn(Optional.of(category));
        when(categoryRepository.save(any(Category.class))).thenReturn(category);
        when(mapper.toResponseDTO(category)).thenReturn(new CategoryResponseDTO());

        CategoryResponseDTO result = categoryService.updateCategoryByCode(code, dto);

        assertNotNull(result);
        assertEquals("new",  category.getName());
        verify(categoryRepository).save(category);
    }
}
