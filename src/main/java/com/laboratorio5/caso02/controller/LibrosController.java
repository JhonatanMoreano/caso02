/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.laboratorio5.caso02.controller;

import com.laboratorio5.caso02.model.Libros;
import com.laboratorio5.caso02.repository.LibrosRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LibrosController {

    @Autowired
    private LibrosRepository librosRepository;

    @GetMapping("/listarLibros")
    public String listarLibros(Model model) {
        List<Libros> libros = librosRepository.findAll();
        model.addAttribute("libros", libros);
        model.addAttribute("libro", new Libros());
        return "listar_libros";
    }

    @PostMapping("/submitLibro")
    public String guardarLibro(@ModelAttribute Libros libro) {
        librosRepository.save(libro);
        return "redirect:/listarLibros";
    }

    @GetMapping("/editarLibro/{id}")
    public String editarLibro(@PathVariable Long id, Model model) {
        Libros libro = librosRepository.findById(id).orElse(null);
        model.addAttribute("libro", libro);
        return "formulario_libros";
    }

    @PostMapping("/submitEdicionLibro")
    public String actualizarLibro(@ModelAttribute Libros libro) {
        librosRepository.save(libro);
        return "redirect:/listarLibros";
    }

    @GetMapping("/eliminarLibro/{id}")
    public String eliminarLibro(@PathVariable Long id) {
        librosRepository.deleteById(id);
        return "redirect:/listarLibros";
    }
}
