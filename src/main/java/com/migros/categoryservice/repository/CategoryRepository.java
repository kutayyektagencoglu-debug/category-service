package com.migros.categoryservice.repository;

import com.migros.categoryservice.enums.CategoryType;
import com.migros.categoryservice.enums.UnitType;
import com.migros.categoryservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, String> {
    boolean existsByCode(String code);
    boolean existsByName(String name);

    Optional<Category> findByCode(String code);
    Optional<Category> findByName(String Name);

    List<Category> findByUnit(UnitType Unit);
    List<Category> findByBrand(String brand);
    List<Category> findByCategoryCode(CategoryType categoryCode);

    //add custom search methods later
}
