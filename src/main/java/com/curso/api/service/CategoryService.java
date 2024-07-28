package com.curso.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.curso.api.dto.CategoryRestBodyDTO;
import com.curso.api.persistence.entities.CategoryEntity;

public interface CategoryService {


    public Page<CategoryEntity> findAll(Pageable pageable);
    public java.util.Optional<CategoryEntity> findById(Long categoryId);
    public CategoryEntity create( CategoryRestBodyDTO saveCategory);
    public CategoryEntity updateById(Long categoryId,  CategoryRestBodyDTO saveCategory);
    public CategoryEntity disableById(Long categoryId);

}
