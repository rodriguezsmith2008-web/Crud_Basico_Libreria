package com.Jhoan.CrudLibreriaChenao.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class LibroRequestDTO {
    private String titulo;
    private String autor;
    private LocalDate fechaPublicacion;
}
