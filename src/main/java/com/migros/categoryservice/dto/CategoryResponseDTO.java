package com.migros.categoryservice.dto;

import com.migros.categoryservice.enums.CategoryType;
import com.migros.categoryservice.enums.UnitType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class CategoryResponseDTO {
    private Long id;
    private String name;
    private String code;
    private String brand;
    private UnitType unit;
    private CategoryType categoryCode;
}
