package com.alura.loja.service;

import com.alura.loja.model.Categoria;
import com.alura.loja.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public void saveCategory(Categoria categoria){
        categoriaRepository.save(categoria);
    }
}
