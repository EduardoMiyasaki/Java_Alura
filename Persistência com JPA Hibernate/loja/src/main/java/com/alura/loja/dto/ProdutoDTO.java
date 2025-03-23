package com.alura.loja.dto;

import com.alura.loja.model.Categoria;

public record ProdutoDTO(String nome,
                         String descricao,
                         double preco,
                         Categoria categoria){}
