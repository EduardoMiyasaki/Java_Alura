package br.com.eduardo.service;

import br.com.eduardo.CepException;
import br.com.eduardo.ConnectionFactory;
import br.com.eduardo.EnderecoDAO;
import br.com.eduardo.EnderecoDTO;
import br.com.eduardo.domain.Endereco;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

public class EnderecoService {

    private ConnectionFactory connection;

    public EnderecoService() {
        this.connection = new ConnectionFactory();
    }

    public void salvarEndereco(EnderecoDTO enderecoDTO) {

        try (Connection conexao = connection.openConnection()) {
            new EnderecoDAO(conexao).salvarEndereco(enderecoDTO);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    public Set<Endereco> listarEnderecos() {
        try (Connection conexao = connection.openConnection()) {
            return new EnderecoDAO(conexao).obterEnderecos();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Endereco listarEndereco(String cep) {

        try (Connection conexao = connection.openConnection()) {
            return new EnderecoDAO(conexao).obterEndereco(cep);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void excluirEndereco(String cep) {

        try(Connection conexao = connection.openConnection()){
             new EnderecoDAO(conexao).excluirEndereco(cep);

        } catch(SQLException e){
            throw new CepException("Cep não encontrado");
        }
    }
}
