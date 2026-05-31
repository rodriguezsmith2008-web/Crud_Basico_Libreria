package com.Jhoan.CrudLibreriaChenao.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Jhoan.CrudLibreriaChenao.entity.LibroEntity;



@Repository
public interface LibroRepository extends JpaRepository<LibroEntity, Long> {

    boolean exexistsBytitle(String name);
}
