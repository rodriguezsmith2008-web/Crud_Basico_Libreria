package com.Jhoan.CrudLibreriaChenao.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class LibroResponseDTO {
    private Long id;
    private String titulo;
    private String autor;
    private LocalDate fechaPublicacion;
    private String estado; 
}