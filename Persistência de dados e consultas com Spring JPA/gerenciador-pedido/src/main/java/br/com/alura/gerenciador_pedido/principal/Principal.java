package br.com.alura.gerenciador_pedido.principal;

import br.com.alura.gerenciador_pedido.model.Categoria;
import br.com.alura.gerenciador_pedido.model.Fornecedor;
import br.com.alura.gerenciador_pedido.model.Pedido;
import br.com.alura.gerenciador_pedido.model.Produto;
import br.com.alura.gerenciador_pedido.repository.CategoriaRepository;
import br.com.alura.gerenciador_pedido.repository.FornecedorRepository;
import br.com.alura.gerenciador_pedido.repository.PedidoRepository;
import br.com.alura.gerenciador_pedido.repository.ProdutoRepository;

import java.time.LocalDate;
import java.util.List;

public class Principal {

    public void exibirMenu(CategoriaRepository categoriaRepository, PedidoRepository pedidoRepository, ProdutoRepository produtoRepository, FornecedorRepository fornecedorRepository) {

        Categoria categoriaEletronicos = new Categoria(null, "Eletrônicos");
        Categoria categoriaLivros = new Categoria(null, "Livros");

        categoriaRepository.save(categoriaEletronicos);
        categoriaRepository.save(categoriaLivros);

        Fornecedor fornecedorTech = new Fornecedor("Tech Supplier");
        Fornecedor fornecedorLivros = new Fornecedor("Livraria Global");
        fornecedorRepository.saveAll(List.of(fornecedorTech, fornecedorLivros));

        Produto produto1 = new Produto("Notebook", 3500.0, categoriaEletronicos, fornecedorTech);
        Produto produto2 = new Produto("Smartphone", 2500.0, categoriaEletronicos, fornecedorTech);
        Produto produto3 = new Produto("Livro de Java", 100.0, categoriaLivros, fornecedorLivros);

        produtoRepository.saveAll(List.of(produto1, produto2, produto3));

        Pedido pedido1 = new Pedido(1L, LocalDate.now());
        Pedido pedido2 = new Pedido(2L, LocalDate.now().minusDays(1));
        pedidoRepository.saveAll(List.of(pedido1, pedido2));
        pedido1.setProdutos(List.of(produto1, produto3));
        pedido2.setProdutos(List.of(produto2));
        categoriaEletronicos.setProdutos(List.of(produto1, produto2));
        categoriaLivros.setProdutos(List.of(produto3));


        // categoriaRepository.saveAll(List.of(categoriaEletronicos, categoriaLivros));

        System.out.println("Categoria e seus produtos");
        categoriaRepository.findAll()
                .forEach(c -> {
                    System.out.println(c.getNome());
                    c.getProdutoList().forEach(Produto::getNome);
                });

    }
}
