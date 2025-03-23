package com.alura.loja.service;

import com.alura.loja.dto.ProdutoDTO;
import com.alura.loja.model.Produto;
import com.alura.loja.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public void saveProduct(ProdutoDTO dto){
        produtoRepository.save(new Produto(dto));
    }
}
