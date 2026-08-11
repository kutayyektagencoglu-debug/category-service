package com.migros.categoryservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("categoryId")
    private Long id;
    @JsonProperty("categoryName")
    private String name;
    @Size(min = 2, max = 2)
    @JsonProperty("categoryCode")
    private String code;
}
