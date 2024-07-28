package com.curso.api.persistence.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.curso.api.persistence.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long>{

    Optional<UserRepository> findByUsername(String username);

}
