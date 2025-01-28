package br.com.alura.bytebank.domain.conta;

import br.com.alura.bytebank.domain.cliente.Cliente;
import br.com.alura.bytebank.domain.cliente.DadosCadastroCliente;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ContaDAO {

    private Connection connection;

    ContaDAO(Connection connection) {
        this.connection = connection;
    }

    public void salvarConta(DadosAberturaConta dadosDaConta) {

        var cliente = new Cliente(dadosDaConta.dadosCliente());
        var conta = new Conta(dadosDaConta.numero(), cliente);

        String sql = "INSERT INTO conta (numero, saldo , cliente_nome, cliente_cpf, cliente_email)" +
                "VALUES(?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // PreparedStatement responsável por setar os valores
            preparedStatement.setInt(1, conta.getNumero());
            preparedStatement.setBigDecimal(2, BigDecimal.ZERO);
            preparedStatement.setString(3, dadosDaConta.dadosCliente().nome());
            preparedStatement.setString(4, dadosDaConta.dadosCliente().cpf());
            preparedStatement.setString(5, dadosDaConta.dadosCliente().email());

            preparedStatement.execute();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Set<Conta> getAllContas() {

        Set<Conta> listaContas = new HashSet<>();
        String sql = "SELECT * FROM conta";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Integer numeroConta = resultSet.getInt(1);
                BigDecimal saldo = resultSet.getBigDecimal(2);
                String nomeClient = resultSet.getString(3);
                String cpfClient = resultSet.getString(4);
                String emailClient = resultSet.getString(5);

                DadosCadastroCliente dadosCadastroCliente = new DadosCadastroCliente(nomeClient, cpfClient, emailClient);
                Cliente client = new Cliente(dadosCadastroCliente);
                listaContas.add(new Conta(numeroConta, client));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return listaContas;

    }

    public Conta getOneConta(int numero) {

        String sql = "SELECT * FROM conta WHERE numero = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, numero);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Integer numeroConta = resultSet.getInt(1);
                BigDecimal saldo = resultSet.getBigDecimal(2);
                String nomeClient = resultSet.getString(3);
                String cpfClient = resultSet.getString(4);
                String emailClient = resultSet.getString(5);

                DadosCadastroCliente dadosCadastroCliente = new DadosCadastroCliente(nomeClient, cpfClient, emailClient);
                Cliente client = new Cliente(dadosCadastroCliente);
                Conta conta = new Conta(numeroConta, client);
                conta.depositar(saldo);
                return conta;
            } else {
                throw new RuntimeException("Conta com o número " + numero + " não encontrada.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
