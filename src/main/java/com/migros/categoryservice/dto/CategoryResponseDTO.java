package com.migros.categoryservice.dto;

import jakarta.validation.constraints.Size;
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
    @Size(min = 2, max = 2)
    private String code;
}
