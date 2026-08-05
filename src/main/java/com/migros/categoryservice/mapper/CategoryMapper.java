package com.migros.categoryservice.mapper;

import com.migros.categoryservice.dto.CategoryRequestDTO;
import com.migros.categoryservice.dto.CategoryResponseDTO;
import com.migros.categoryservice.model.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryResponseDTO toResponseDTO(Category category);
    Category toEntity(CategoryRequestDTO requestDTO);
    List<CategoryResponseDTO> toResponseDTOList(List<Category> categories);
}
