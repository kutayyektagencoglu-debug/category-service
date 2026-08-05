package com.migros.categoryservice.model;

import com.migros.categoryservice.enums.UnitType;
import com.migros.categoryservice.enums.CategoryType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Category {

    @Id
    private String name;

    @Column(unique = true, length = 5)
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoryType categoryCode;

    @Column(nullable = false)
    private String brand;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UnitType unit;
}