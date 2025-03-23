package com.alura.loja.controller;

import com.alura.loja.model.Categoria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class CadastroProduto {

    public static void main(String[] args) {
        Categoria celulares = new Categoria("celulares");

        EntityManagerFactory factory = Persistence.
                createEntityManagerFactory("loja");

        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();

        em.persist(celulares);

        em.getTransaction().commit();

        em.clear();

       celulares =  em.merge(celulares);

        celulares.setNome("1234");

        em.getTransaction().commit();

        em.close();

    }
}
