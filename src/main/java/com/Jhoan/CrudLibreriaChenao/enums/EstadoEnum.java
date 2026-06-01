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

     public static Long disponible() {
        return Disponible.getId();
    }

    public static Long prestado() {
        return Prestado.getId();
    }

    public static String getNombre(Long id) {
    for (EstadoEnum estado : EstadoEnum.values()) {
        if (estado.getId().equals(id)) {
            return estado.name();
        }
    }
    return null;
}
}