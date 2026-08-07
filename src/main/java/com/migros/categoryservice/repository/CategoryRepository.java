package com.migros.categoryservice.repository;

import com.migros.categoryservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, String> {
    boolean existsById(Long id);
    boolean existsByName(String name);
    boolean existsByCode(String code);

    Optional<Category> findByCode(String code);
    Optional<Category> findByName(String Name);
    Optional<Category> findById(Long id);

    void deleteById(Long id);
    void deleteByName(String name);
    void deleteByCode(String code);

    //add custom search methods later
}
