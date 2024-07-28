package com.curso.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.api.dto.CategoryRestBodyDTO;
import com.curso.api.exception.ObjectNotFoundException;
import com.curso.api.persistence.entities.CategoryEntity;
import com.curso.api.service.CategoryService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Page<CategoryEntity>> findAll(Pageable pageable) {

        Page<CategoryEntity> categoriesPage = categoryService.findAll(pageable);

        if (categoriesPage.hasContent()) {
            return ResponseEntity.ok(categoriesPage);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryEntity> findOneById(@PathVariable Long categoryId) {
        CategoryEntity category = categoryService.findById(categoryId)
                .orElseThrow(() -> new ObjectNotFoundException("no se encontró la categoría con el id: " + categoryId));
        return ResponseEntity.ok(category);
    }

    @PostMapping
    public ResponseEntity<CategoryEntity> createOne(@RequestBody @Valid CategoryRestBodyDTO saveCategory) {
        CategoryEntity category = categoryService.create(saveCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryEntity> updateOneById(@PathVariable Long categoryId,
            @RequestBody @Valid CategoryRestBodyDTO saveCategory) {
        CategoryEntity category = categoryService.updateById(categoryId, saveCategory);
        return ResponseEntity.ok(category);
    }

    @PutMapping("/{categoryId}/disabled")
    public ResponseEntity<CategoryEntity> disableOneById(@PathVariable Long categoryId) {
        CategoryEntity category = categoryService.disableById(categoryId);
        return ResponseEntity.ok(category);
    }

}