package br.com.alura.gerenciador_pedido.repository;

import br.com.alura.gerenciador_pedido.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    // Retorne pedidos que ainda não possuem uma data de entrega.
    List<Pedido> findByDataIsNull();

    // Retorne pedidos com data de entrega preenchida.
    List<Pedido> findByDataIsNotNull();

    // Retorne pedidos feitos após uma data específica.
    List<Pedido> findByDataAfter(LocalDate data);

    // Retorne pedidos feitos antes de uma data específica.
    List<Pedido> findByDataBefore(LocalDate data);

    // Retorne pedidos feitos em um intervalo de datas.
    List<Pedido> findByDataBetween(LocalDate inicio, LocalDate fim);

    // Crie uma consulta que retorne os pedidos feitos entre duas datas.
    @Query("SELECT p FROM Pedido p WHERE p.data BETWEEN :inicio AND :fim")
    List<Pedido> pedidosEntreAsDatas(@Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim);
}
