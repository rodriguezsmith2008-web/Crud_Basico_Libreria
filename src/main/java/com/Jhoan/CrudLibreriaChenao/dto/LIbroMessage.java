package com.Jhoan.CrudLibreriaChenao.dto;

import lombok.Data;

@Data
public class LibroMessage<T> {
    private T data;
    private String message;
}