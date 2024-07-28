package com.curso.api.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.curso.api.dto.ProductRestBodyDTO;
import com.curso.api.persistence.entities.ProductEntity;

public interface ProductService {
    Page<ProductEntity> findAll(Pageable pageable);

    Optional<ProductEntity> findById(Long productId);

    ProductEntity create(ProductRestBodyDTO request);

    ProductEntity update(Long productId,ProductRestBodyDTO request) ;

    ProductEntity disabled(Long productId);
} 
