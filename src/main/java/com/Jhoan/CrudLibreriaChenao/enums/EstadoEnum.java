package com.Jhoan.CrudLibreriaChenao.enums;

public enum EstadoEnum {
    Disponible(1L),
    Prestado(2L);

    private final Long id;

    EstadoEnum(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}