package com.migros.categoryservice.integrationtest;

import com.migros.categoryservice.dto.CategoryRequestDTO;
import com.migros.categoryservice.dto.CategoryResponseDTO;
import com.migros.categoryservice.model.Category;
import com.migros.categoryservice.repository.CategoryRepository;
import com.migros.categoryservice.service.CategoryService;
import com.migros.commonerror.exception.BusinessException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class CategoryIntegrationTest {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void createCategoryInvalidCode() {
        CategoryRequestDTO dto = new CategoryRequestDTO();
        dto.setCode("null");
        dto.setName("test");

        assertThrows(BusinessException.class, () -> categoryService.createCategory(dto));

        assertTrue(categoryRepository.findAll().isEmpty());
    }

    @Test
    public void createCategorySuccess(){
        CategoryRequestDTO dto = new CategoryRequestDTO();
        dto.setName("test");
        dto.setCode("ts");
        CategoryResponseDTO result = categoryService.createCategory(dto);

        assertEquals("test",  result.getName());
        assertEquals("TS", result.getCode());

        List<Category> saved  = categoryRepository.findAll();
        assertFalse(saved.isEmpty());
        assertEquals("test", saved.getFirst().getName());
        assertEquals("TS", saved.getFirst().getCode());
    }

    @Test
    public void getCategoryByCodeThrowsNotFound() {
        CategoryRequestDTO dto = new CategoryRequestDTO();
        dto.setName("test");
        dto.setCode("TS");
        categoryService.createCategory(dto);

        assertThrows(BusinessException.class, () -> categoryService.getCategoryByCode("BS"));
    }

    @Test
    public void getCategoryByCodeSuccess(){
        CategoryRequestDTO dto1 = new CategoryRequestDTO();
        dto1.setName("test");
        dto1.setCode("TS");
        categoryService.createCategory(dto1);

        CategoryRequestDTO dto2 = new CategoryRequestDTO();
        dto2.setName("fake");
        dto2.setCode("FK");
        categoryService.createCategory(dto2);

        CategoryResponseDTO result = categoryService.getCategoryByCode("TS");

        assertEquals("test", result.getName());
        assertEquals("TS", result.getCode());
    }

    @Test
    public void updateCategoryThrowsWrongCode(){
        CategoryRequestDTO dto = new CategoryRequestDTO();
        dto.setName("test");
        dto.setCode("TS");
        categoryService.createCategory(dto);

        CategoryRequestDTO desiredDTO = new CategoryRequestDTO();
        desiredDTO.setName("wrong");
        desiredDTO.setCode("WR");

        assertThrows(BusinessException.class, () -> categoryService.updateCategoryByCode("test",  desiredDTO));

        Category saved = categoryRepository.findByCode("TS").orElseThrow();
        assertEquals("test", saved.getName());
        assertEquals("TS", saved.getCode());
    }
    @Test
    public void updateCategorySuccess(){
        CategoryRequestDTO dto = new CategoryRequestDTO();
        dto.setName("test");
        dto.setCode("TS");
        categoryService.createCategory(dto);

        CategoryRequestDTO desiredDTO = new CategoryRequestDTO();
        desiredDTO.setName("newTest");
        desiredDTO.setCode("TS");

        CategoryResponseDTO result = categoryService.updateCategoryByCode("TS",  desiredDTO);

        assertEquals("newTest", result.getName());

        Category saved = categoryRepository.findByCode("TS").orElseThrow();
        assertEquals("newTest", saved.getName());
    }
}
