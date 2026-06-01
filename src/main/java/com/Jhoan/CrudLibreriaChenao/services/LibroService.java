package com.Jhoan.CrudLibreriaChenao.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Jhoan.CrudLibreriaChenao.dto.LibroMessage;
import com.Jhoan.CrudLibreriaChenao.dto.LibroRequestDTO;
import com.Jhoan.CrudLibreriaChenao.dto.LibroResponseDTO;
import com.Jhoan.CrudLibreriaChenao.entity.LibroEntity;
import com.Jhoan.CrudLibreriaChenao.enums.EstadoEnum;
import com.Jhoan.CrudLibreriaChenao.repository.LibroRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LibroService {

    public final LibroRepository libroRepository;

    public LibroMessage<LibroRequestDTO> ingresarLibro(LibroRequestDTO libro) {
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
        libroEntity.setEstado(EstadoEnum.disponible());

        libroRepository.save(libroEntity);
        response.setMessage("Libro ingresado exitosamente a la plataforma");
        return response;
    }

    public List<LibroResponseDTO> listarLibros() {
        List<LibroEntity> entidades = libroRepository.findAll();
        List<LibroResponseDTO> responseDTOs = new ArrayList<>();

        for (LibroEntity libros : entidades) {
            LibroResponseDTO responseDTO = new LibroResponseDTO();
            responseDTO.setId(libros.getId());
            responseDTO.setAutor(libros.getAutor());
            responseDTO.setTitulo(libros.getTitulo());
            responseDTO.setFechaPublicacion(libros.getFechaPublicacion());
            responseDTO.setEstado(EstadoEnum.getNombre(libros.getEstado()));
            responseDTOs.add(responseDTO);
        }
        return responseDTOs;
    }

    public LibroResponseDTO buscarLibro(String titulo) {
        Optional<LibroEntity> optional = libroRepository.findByTitulo(titulo);

        if (!optional.isPresent()) {
            return null;
        }

        LibroEntity libro = optional.get();
        LibroResponseDTO response = new LibroResponseDTO();
        response.setId(libro.getId());
        response.setTitulo(libro.getTitulo());
        response.setAutor(libro.getAutor());
        response.setFechaPublicacion(libro.getFechaPublicacion());
        response.setEstado(EstadoEnum.getNombre(libro.getEstado()));
        return response;
    }

    public LibroMessage<LibroRequestDTO> actualizarLibro(LibroRequestDTO reques, String titulo) {
        LibroMessage<LibroRequestDTO> response = new LibroMessage<>();
        Optional<LibroEntity> actualizar = libroRepository.findByTitulo(titulo);

        if (!actualizar.isPresent()) {
            response.setMessage("El libro no se encuentra guardado en el programa");
            return response;
        }

        LibroEntity libro = actualizar.get();

        if (libro.getEstado().equals(EstadoEnum.prestado())) {
            response.setMessage("En estos momento el libro se encuentra prestado no se puede actualizar");
            return response;
        }

        if (reques.getTitulo() != null) {
            libro.setTitulo(reques.getTitulo());
        }

        if (reques.getAutor() != null) {
            libro.setAutor(reques.getAutor());
        }

        if (reques.getFechaPublicacion() != null) {
            libro.setFechaPublicacion(reques.getFechaPublicacion());
        }

        libroRepository.save(libro);
        response.setMessage("Libro actualizado exitosamente");
        return response;
    }

    public LibroMessage<String> eliminarLibro(String titulo) {
        LibroMessage<String> response = new LibroMessage<>();
        Optional<LibroEntity> eliminar = libroRepository.findByTitulo(titulo);

        if (!eliminar.isPresent()) {
            response.setMessage("El libro no existe");
            return response;
        }

        LibroEntity libro = eliminar.get();

        if (libro.getEstado().equals(EstadoEnum.prestado())) {
            response.setMessage("El libro esta prestado no se encuentra disponible para eliminar");
            return response;
        }

        libroRepository.delete(libro);
        response.setMessage("Libro eliminado exitosamente");
        return response;
    }

    public LibroMessage<String> prestarLibro(String titulo) {
        LibroMessage<String> response = new LibroMessage<>();
        Optional<LibroEntity> prestar = libroRepository.findByTitulo(titulo);

        if (!prestar.isPresent()) {
            response.setMessage("El libro no existe");
            return response;
        }

        LibroEntity libro = prestar.get();

        if (libro.getEstado().equals(EstadoEnum.prestado())) {
            response.setMessage("El libro ya esta prestado");
            return response;
        }

        libro.setEstado(EstadoEnum.prestado());
        libroRepository.save(libro);
        response.setMessage("Libro prestado exitosamente");
        return response;
    }

    public LibroMessage<String> devolverLibro(String titulo) {
        LibroMessage<String> response = new LibroMessage<>();
        Optional<LibroEntity> devolver = libroRepository.findByTitulo(titulo);

        if (!devolver.isPresent()) {
            response.setMessage("El libro no existe");
            return response;
        }

        LibroEntity libro = devolver.get();

        if (libro.getEstado().equals(EstadoEnum.disponible())) {
            response.setMessage("El libro ya esta disponible");
            return response;
        }

        libro.setEstado(EstadoEnum.disponible());
        libroRepository.save(libro);
        response.setMessage("Libro devuelto exitosamente");
        return response;
    }
}