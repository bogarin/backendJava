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

import com.curso.api.dto.ProductRestBodyDTO;
import com.curso.api.exception.ObjectNotFoundException;
import com.curso.api.persistence.entities.ProductEntity;
import com.curso.api.service.ProductService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    /**
     * @param pageable
     * @return
     */
    @GetMapping()
    public ResponseEntity<Page<ProductEntity>> findAll(Pageable pageable) {
        Page<ProductEntity> productsPage = productService.findAll(pageable);
        if (productsPage.hasContent()) {
            return ResponseEntity.ok(productsPage);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductEntity> findById(@PathVariable Long productId) {
        ProductEntity products = productService.findById(productId)
                .orElseThrow(() -> new ObjectNotFoundException("Producto no encontrado con ID: " + productId));
        return ResponseEntity.ok(products);

    }

    @PostMapping()
    public ResponseEntity<ProductEntity> create(@RequestBody @Valid ProductRestBodyDTO request) {
        ProductEntity product = productService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductEntity> update(@PathVariable Long productId,
            @RequestBody @Valid ProductRestBodyDTO request) {
        ProductEntity product = productService.update(productId, request);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/{productId}/disabled")
    public ResponseEntity<ProductEntity> disabled(@PathVariable Long productId) {
        ProductEntity product = productService.disabled(productId);
        return ResponseEntity.ok(product);
    }

}
