package com.alura.loja.controller;

import com.alura.loja.dto.ProdutoDTO;
import com.alura.loja.model.Produto;
import com.alura.loja.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<ProdutoDTO> saveProduct(@RequestBody ProdutoDTO dto) {
        produtoService.saveProduct(dto);
        return ResponseEntity.status(201).body(dto);
    }
}
