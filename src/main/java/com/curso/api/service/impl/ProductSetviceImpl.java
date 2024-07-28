package com.curso.api.service.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.curso.api.dto.ProductRestBodyDTO;
import com.curso.api.exception.ObjectNotFoundException;
import com.curso.api.persistence.entities.CategoryEntity;
import com.curso.api.persistence.entities.ProductEntity;
import com.curso.api.persistence.entities.ProductEntity.ProductStatus;
import com.curso.api.persistence.repositories.ProductRepository;
import com.curso.api.service.ProductService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ProductSetviceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Page<ProductEntity> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Optional<ProductEntity> findById(Long productId) {
        return productRepository.findById(productId);
    }

    @Override
    public ProductEntity create(ProductRestBodyDTO request) {
        ProductEntity entity = ProductEntity.builder()
                .name(request.getName())
                .price(request.getPrice())
                .status(ProductStatus.ENABLED)
                .category(CategoryEntity.builder().id(request.getCategoryId()).build())
                .build();
        return productRepository.save(entity);

    }

    @Override
    public ProductEntity update(Long productId, ProductRestBodyDTO request) {
        ProductEntity entity = productRepository.findById(productId)
                .orElseThrow(() -> new ObjectNotFoundException("Producto no encontrado con ID: " + productId));
        entity.setName(request.getName());
        entity.setPrice(request.getPrice());
        return productRepository.save(entity);
    }

    @Override
    public ProductEntity disabled(Long productId) {
        ProductEntity entity = productRepository.findById(productId)
                .orElseThrow(() -> new ObjectNotFoundException("Producto no encontrado con ID: " + productId));
        entity.setStatus(ProductStatus.DISABLED);
        return productRepository.save(entity);
    }

}
