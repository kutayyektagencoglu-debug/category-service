package com.migros.categoryservice.mapper;

import com.migros.categoryservice.dto.CategoryDTO;
import com.migros.categoryservice.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDTO toDTO(Category category);
    Category toEntity(CategoryDTO dto);
}
