package br.com.alura.gerenciador_pedido.repository;

import br.com.alura.gerenciador_pedido.model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
}
