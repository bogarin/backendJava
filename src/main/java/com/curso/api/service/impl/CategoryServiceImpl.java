package com.curso.api.service.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.curso.api.dto.CategoryRestBodyDTO;
import com.curso.api.exception.ObjectNotFoundException;
import com.curso.api.persistence.entities.CategoryEntity;
import com.curso.api.persistence.entities.CategoryEntity.CategoryStatus;
import com.curso.api.persistence.repositories.CategoryRepository;
import com.curso.api.service.CategoryService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Page<CategoryEntity> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Optional<CategoryEntity> findById(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }

    @Override
    public CategoryEntity create(CategoryRestBodyDTO saveCategory) {
        CategoryEntity entity = CategoryEntity.builder()
                .name(saveCategory.getName())
                .status(CategoryStatus.ENABLED)
                .build();
        return categoryRepository.save(entity);
    }

    @Override
    public CategoryEntity updateById(Long categoryId, CategoryRestBodyDTO saveCategory) {
        CategoryEntity entity = categoryRepository.findById(categoryId)
                .orElseThrow(
                        () -> new ObjectNotFoundException("No se encontró una categoría con el id: " + categoryId));
        entity.setName(saveCategory.getName());
        return categoryRepository.save(entity);
    }

    @Override
    public CategoryEntity disableById(Long categoryId) {
        CategoryEntity entity = categoryRepository.findById(categoryId)
                .orElseThrow(
                        () -> new ObjectNotFoundException("No se encontró una categoría con el id: " + categoryId));
        entity.setStatus(CategoryStatus.DISABLED);
        return categoryRepository.save(entity);
    }

}
