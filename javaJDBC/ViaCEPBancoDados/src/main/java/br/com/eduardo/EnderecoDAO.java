package br.com.eduardo;

import br.com.eduardo.domain.Endereco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class EnderecoDAO {

    private Connection connection;

    public EnderecoDAO(Connection connection) {
        this.connection = connection;
    }

    public void salvarEndereco(EnderecoDTO enderecoDTO) {

        String sql = "INSERT INTO endereco (cep , rua , bairro, cidade , uf , estado , regiao)" +
                "VALUES(?, ?, ?, ?, ?, ? ,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, enderecoDTO.cep());
            preparedStatement.setString(2, enderecoDTO.logradouro());
            preparedStatement.setString(3, enderecoDTO.bairro());
            preparedStatement.setString(4, enderecoDTO.localidade());
            preparedStatement.setString(5, enderecoDTO.uf());
            preparedStatement.setString(6, enderecoDTO.estado());
            preparedStatement.setString(7, enderecoDTO.regiao());

            preparedStatement.execute();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Set<Endereco> obterEnderecos() {

        Set<Endereco> enderecos = new HashSet<>();
        String sql = "SELECT * FROM endereco";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String cep = resultSet.getString(1);
                String logradouro = resultSet.getString(2);
                String bairro = resultSet.getString(3);
                String cidade = resultSet.getString(4);
                String uf = resultSet.getString(5);
                String estado = resultSet.getString(6);
                String regiao = resultSet.getString(7);

                Endereco endereco = new Endereco(new EnderecoDTO(cep, logradouro, bairro, cidade, uf, estado, regiao));
                enderecos.add(endereco);
            }

            preparedStatement.close();
            resultSet.close();
            connection.close();
            return enderecos;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Endereco obterEndereco(String cep) {

        String sql = "SELECT * from endereco WHERE cep = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, cep);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String logradouro = resultSet.getString(2);
                String bairro = resultSet.getString(3);
                String cidade = resultSet.getString(4);
                String uf = resultSet.getString(5);
                String estado = resultSet.getString(6);
                String regiao = resultSet.getString(7);

                connection.close();
                resultSet.close();
                preparedStatement.close();
                return new Endereco(new EnderecoDTO(cep, logradouro, bairro, cidade, uf, estado, regiao));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }

    public void excluirEndereco(String cep) {

        String sql = "DELETE FROM endereco WHERE cep = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            connection.setAutoCommit(false);

            preparedStatement.setString(1, cep);
            preparedStatement.execute();

            connection.commit();
            connection.close();

        } catch (SQLException e) {
            throw new CepException("Cep não encontrado");
        }
    }
}
