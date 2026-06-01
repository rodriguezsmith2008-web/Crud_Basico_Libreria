package com.Jhoan.CrudLibreriaChenao.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Jhoan.CrudLibreriaChenao.entity.LibroEntity;



@Repository
public interface LibroRepository extends JpaRepository<LibroEntity, Long> {

    boolean existsByTitulo(String titulo);
    Optional<LibroEntity> findByTitulo(String titulo);
}
