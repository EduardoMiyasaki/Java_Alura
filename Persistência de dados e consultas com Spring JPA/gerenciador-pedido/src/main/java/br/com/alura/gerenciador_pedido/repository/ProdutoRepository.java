package br.com.alura.gerenciador_pedido.repository;

import br.com.alura.gerenciador_pedido.model.Categoria;
import br.com.alura.gerenciador_pedido.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.sound.sampled.Port;
import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Produto findByNomeIgnoreCase(String nome);

    // Retorne todos os produtos associados a uma categoria específica.
    List<Produto> findByCategoria(Categoria categoria);

    List<Produto> findByPrecoGreaterThanEqual(double preco);

    List<Produto> findByPrecoLessThanEqual(double preco);

    List<Produto> findByNomeContainingIgnoreCase(String nome);

    //Retorne produtos de uma categoria ordenados pelo preço de forma crescente.
    List<Produto> findByCategoriaOrderByPreco(Categoria categoria);

    // Retorne produtos de uma categoria ordenados pelo preço de forma decrescente.
    List<Produto> findByCategoriaOrderByPrecoDesc(Categoria categoria);

    //Retorne a contagem de produtos em uma categoria específica.
    int countByCategoria(Categoria categoria);

    // Retorne a contagem de produtos cujo preço seja maior que o valor fornecido.
    int countByPrecoGreaterThanEqual(double preco);

    // Retorne produtos com preço menor que o valor fornecido ou cujo nome contenha o termo especificado.
    List<Produto> findByPrecoLessThanEqualOrNomeContainingIgnoreCase(Double preco, String termo);

    // Retorne os três produtos mais caros.
    List<Produto> findTop3OrderByPrecoDesc();

    //Retorne os cinco produtos mais baratos de uma categoria.
    List<Produto> findTop5ByCategoriaOrderByPreco(Categoria categoria);

    @Query("SELECT p FROM Produto p WHERE p.preco >= :preco")
    List<Produto> filtroPreco(@Param("preco") Double preco);

    @Query("SELECT p FROM Produto p ORDER BY p.preco")
    List<Produto> ordenarPorPreco();

    @Query("SELECT p FROM Produto p ORDER By p.preco DESC")
    List<Produto> ordernarPorPrecoDescrecente();

    // Crie uma consulta que retorne os produtos que comecem com uma letra específica.
    @Query("SELECT p FROM Produto p WHERE p.nome LIKE :letra% ")
    List<Produto> produtosQueComecaCom(@Param("letra") String letra);

    @Query("SELECT AVG(p.preco) FROM Produto p")
    double mediaPreco();

    @Query("SELECT MAX(p.preco) FROM Produto p WHERE p.categoria.nome = :categoria")
    double maiorPrecoPorCategoria(@Param("categoria") String categoria);

    @Query("SELECT c.nome, COUNT(p) FROM Produto p INNER JOIN p.categoria c GROUP BY c.nome")
    int numeroProdutosPorCategoria(@Param("categoria") String categoria);

    // Crie uma consulta para filtrar categorias com mais de 10 produtos.
    // @Query("SELECT c.nome, COUNT(p) FROM Produto p JOIN p.categoria c GROUP BY c.nome HAVING COUNT(p) > :quantidade")

    // Crie uma consulta para retornar os produtos filtrados por nome ou por categoria.
//    @Query("SELECT p FROM Produto p WHERE categoria = :categoria OR nome = :nome")
//    List<Produto> produtosPorNomeOuCategoria(String nome, Categoria categoria);

    @Query("SELECT p FROM Produto p WHERE (:nome IS NULL OR p.nome = :nome) AND (:categoria IS NULL OR p.categoria.nome = :categoria)")
    List<Produto> buscarProdutosFiltrados(@Param("nome") String nome, @Param("categoria") String categoria);

    // Crie uma consulta nativa para buscar os cinco produtos mais caros
    @Query(value = "SELECT * from produto ORDER BY preco DESC LIMIT 5", nativeQuery = true)
    List<Produto> produtosMaisCaros();
}
