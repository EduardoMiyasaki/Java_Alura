package br.com.alura.gerenciador_pedido.repository;

import br.com.alura.gerenciador_pedido.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
