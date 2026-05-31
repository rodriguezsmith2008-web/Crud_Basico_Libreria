package com.Jhoan.CrudLibreriaChenao.services;


import org.springframework.stereotype.Service;

import com.Jhoan.CrudLibreriaChenao.dto.LibroMessage;
import com.Jhoan.CrudLibreriaChenao.dto.LibroRequestDTO;
import com.Jhoan.CrudLibreriaChenao.entity.LibroEntity;
import com.Jhoan.CrudLibreriaChenao.enums.EstadoEnum;
import com.Jhoan.CrudLibreriaChenao.repository.LibroRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LibroService {
    
    public final LibroRepository libroRepository;

public LibroMessage<LibroRequestDTO> ingresarLibro(LibroRequestDTO libro){
    LibroMessage<LibroRequestDTO> response = new LibroMessage<>();
    boolean optional = libroRepository.exexistsBytitle(libro.getTitulo());
    if (optional) {
        response.setMessage("El libro ya esta ingresado en la plataforma");
        return response;
    }
    LibroEntity libroEntity = new LibroEntity();
    libroEntity.setTitulo(libro.getTitulo());
    libroEntity.setAutor(libro.getAutor());
    libroEntity.setFechaPublicacion(libro.getFechaPublicacion());
    libroEntity.setEstado(EstadoEnum.Disponible.getId());

    libroRepository.save(libroEntity);
    response.setMessage("Libro ingresado exitosamente a la plataforma");
    return response;
}


}
