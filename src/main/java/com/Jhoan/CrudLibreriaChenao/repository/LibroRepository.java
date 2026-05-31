package com.Jhoan.CrudLibreriaChenao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Jhoan.CrudLibreriaChenao.entity.Libros;

@Repository
public interface LibroRepository extends JpaRepository<Libros, Long> {

    
}
