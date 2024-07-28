package com.curso.api.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.api.persistence.entities.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

}
