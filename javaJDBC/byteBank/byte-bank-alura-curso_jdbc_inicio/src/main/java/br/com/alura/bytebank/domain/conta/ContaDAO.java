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
        var conta = new Conta(dadosDaConta.numero(), cliente, true);

        String sql = "INSERT INTO conta (numero, saldo , cliente_nome, cliente_cpf, cliente_email , esta_ativa)" +
                "VALUES(?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // PreparedStatement responsável por setar os valores
            preparedStatement.setInt(1, conta.getNumero());
            preparedStatement.setBigDecimal(2, BigDecimal.ZERO);
            preparedStatement.setString(3, dadosDaConta.dadosCliente().nome());
            preparedStatement.setString(4, dadosDaConta.dadosCliente().cpf());
            preparedStatement.setString(5, dadosDaConta.dadosCliente().email());
            preparedStatement.setBoolean(6, true);

            preparedStatement.execute();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Set<Conta> getAllContas() {

        Set<Conta> listaContas = new HashSet<>();
        String sql = "SELECT * FROM conta WHERE esta_ativa = true";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Integer numeroConta = resultSet.getInt(1);
                BigDecimal saldo = resultSet.getBigDecimal(2);
                String nomeClient = resultSet.getString(3);
                String cpfClient = resultSet.getString(4);
                String emailClient = resultSet.getString(5);
                Boolean estaAtiva = resultSet.getBoolean(6);

                DadosCadastroCliente dadosCadastroCliente = new DadosCadastroCliente(nomeClient, cpfClient, emailClient);
                Cliente client = new Cliente(dadosCadastroCliente);
                listaContas.add(new Conta(numeroConta, saldo, client, estaAtiva));
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
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
                Integer numeroConta = resultSet.getInt("numero");
                BigDecimal saldo = resultSet.getBigDecimal(2);
                String nomeClient = resultSet.getString(3);
                String cpfClient = resultSet.getString(4);
                String emailClient = resultSet.getString(5);
                Boolean estaAtiva = resultSet.getBoolean(6);

                DadosCadastroCliente dadosCadastroCliente = new DadosCadastroCliente(nomeClient, cpfClient, emailClient);
                Cliente client = new Cliente(dadosCadastroCliente);
                Conta conta = new Conta(numeroConta, saldo, client, estaAtiva);

                resultSet.close();
                preparedStatement.close();
                connection.close();
                return conta;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void depositar(int numeroConta, BigDecimal valor) {

        String sql = "UPDATE conta SET saldo = saldo + ? WHERE numero = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            preparedStatement.setBigDecimal(1, valor);
            preparedStatement.setInt(2, numeroConta);

            preparedStatement.executeUpdate();
            connection.commit();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            try {
                connection.rollback();
                connection.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e.getMessage());
        }
    }

    public void sacar(int numeroConta, BigDecimal valor) {

        String sql = "UPDATE conta SET saldo = saldo - ? WHERE numero = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            preparedStatement.setBigDecimal(1, valor);
            preparedStatement.setInt(2, numeroConta);

            preparedStatement.executeUpdate();
            connection.commit();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            try {
                connection.rollback();
                connection.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex.getMessage());
            }
            throw new RuntimeException(e.getMessage());
        }
    }

    public void excluirConta(int numeroConta) {

        String sql = "DELETE FROM conta WHERE numero = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);

            preparedStatement.setInt(1, numeroConta);
            preparedStatement.execute();

            connection.commit();
            connection.close();

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex.getMessage());
            }
            throw new RuntimeException(e.getMessage());
        }
    }

    public void desativarConta(int numeroConta) {

        String sql = "UPDATE conta SET esta_ativa = false WHERE numero = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            connection.setAutoCommit(false);

            preparedStatement.setInt(1, numeroConta);

            preparedStatement.executeUpdate();
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            try {
                connection.rollback();
                connection.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex.getMessage());
            }
            throw new RuntimeException(e.getMessage());
        }

    }

    public void reativarConta(int numeroConta) {
        String sql = "UPDATE conta SET esta_ativa = true WHERE numero = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            connection.setAutoCommit(false);

            preparedStatement.setInt(1, numeroConta);

            preparedStatement.executeUpdate();
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            try {
                connection.rollback();
                connection.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex.getMessage());
            }
            throw new RuntimeException(e.getMessage());
        }

    }
}
