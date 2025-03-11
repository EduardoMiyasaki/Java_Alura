package br.com.alura.gerenciador_pedido;

import br.com.alura.gerenciador_pedido.principal.Principal;
import br.com.alura.gerenciador_pedido.repository.CategoriaRepository;
import br.com.alura.gerenciador_pedido.repository.FornecedorRepository;
import br.com.alura.gerenciador_pedido.repository.PedidoRepository;
import br.com.alura.gerenciador_pedido.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GerenciadorPedidoApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private FornecedorRepository fornecedorRepository;

	public static void main(String[] args) {
		SpringApplication.run(GerenciadorPedidoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.exibirMenu(categoriaRepository, pedidoRepository, produtoRepository, fornecedorRepository	);
	}
}
