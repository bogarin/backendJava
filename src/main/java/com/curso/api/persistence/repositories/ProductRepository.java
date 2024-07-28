package com.curso.api.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.curso.api.persistence.entities.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Long> {

}
