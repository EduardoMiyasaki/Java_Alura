package com.alura.loja.controller;

import com.alura.loja.model.Categoria;
import com.alura.loja.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<Categoria> saveProduct(@RequestBody Categoria categoria) {
        categoriaService.saveCategory(categoria);
        return ResponseEntity.status(201).body(categoria);
    }
}
