package com.Jhoan.CrudLibreriaChenao.controller;
 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
 
import com.Jhoan.CrudLibreriaChenao.dto.LibroRequestDTO;
import com.Jhoan.CrudLibreriaChenao.services.LibroService;
 
import lombok.RequiredArgsConstructor;
 
@Controller
@RequestMapping("/libros")
@RequiredArgsConstructor
public class LibroController {

    private final LibroService libroService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("libros", libroService.listarLibros());
        return "libros/listar"; 
    }

    @GetMapping("/crear")
    public String formularioCrear(Model model) {
        model.addAttribute("libro", new LibroRequestDTO());
        return "libros/crear"; 
    }

    @PostMapping("/crear")
    public String crear(@ModelAttribute LibroRequestDTO libro, Model model) {
        var response = libroService.ingresarLibro(libro);
        model.addAttribute("mensaje", response.getMessage());
        return "redirect:/libros";
    }

    @GetMapping("/actualizar/{titulo}")
    public String formularioActualizar(@PathVariable String titulo, Model model) {
        model.addAttribute("libro", new LibroRequestDTO());
        model.addAttribute("titulo", titulo);
        return "libros/actualizar";
    }

    @PostMapping("/actualizar/{titulo}")
    public String actualizar(@PathVariable String titulo, @ModelAttribute LibroRequestDTO libro, Model model) {
        var response = libroService.actualizarLibro(libro, titulo);
        model.addAttribute("mensaje", response.getMessage());
        return "redirect:/libros";
    }

    @GetMapping("/eliminar/{titulo}")
    public String eliminar(@PathVariable String titulo) {
        libroService.eliminarLibro(titulo);
        return "redirect:/libros";
    }

    @GetMapping("/prestar/{titulo}")
    public String prestar(@PathVariable String titulo) {
        libroService.prestarLibro(titulo);
        return "redirect:/libros";
    }

    @GetMapping("/devolver/{titulo}")
    public String devolver(@PathVariable String titulo) {
        libroService.devolverLibro(titulo);
        return "redirect:/libros";
    }

    @GetMapping("/buscar/{titulo}")
    public String buscar(@PathVariable String titulo, Model model) {
        model.addAttribute("libro", libroService.buscarLibro(titulo));
        return "libros/detalle";
    }

    @GetMapping("/buscar")
public String formularioBuscar() {
    return "libros/buscar";
}


}