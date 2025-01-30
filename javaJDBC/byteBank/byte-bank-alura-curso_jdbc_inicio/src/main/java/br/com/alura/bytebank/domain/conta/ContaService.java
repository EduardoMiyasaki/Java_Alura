package br.com.alura.bytebank.domain.conta;

import br.com.alura.bytebank.ConnectionFactory;
import br.com.alura.bytebank.domain.RegraDeNegocioException;
import br.com.alura.bytebank.domain.cliente.Cliente;
import com.mysql.cj.xdevapi.PreparableStatement;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class ContaService {

    private ConnectionFactory connection;

    public ContaService() {
        this.connection = new ConnectionFactory();
    }

    public Set<Conta> listarContasAbertas() {
        Connection connection = this.connection.openConnection();
        return new ContaDAO(connection).getAllContas();

    }

    public Conta listarConta(int numero) {
        Connection connection1 = this.connection.openConnection();
        Conta conta = new ContaDAO(connection1).getOneConta(numero);
        if (conta == null) {
            throw new RegraDeNegocioException("Não existe conta cadatrada com o número: " + numero);
        }

        return conta;
    }

    public BigDecimal consultarSaldo(Integer numeroDaConta) {
        var conexao = connection.openConnection();
        var conta = listarConta(numeroDaConta);

        Conta conta1 = new ContaDAO(conexao).getOneConta(conta.getNumero());
        if (!conta1.getEstaAtiva()) {
            throw new RegraDeNegocioException("Conta desativida");
        }
        return conta1.getSaldo();
    }

    public void abrir(DadosAberturaConta dadosDaConta) {
        Connection connection = this.connection.openConnection();
        new ContaDAO(connection).salvarConta(dadosDaConta);

    }

    public void realizarSaque(Integer numeroDaConta, BigDecimal valor) {
        Connection connection = this.connection.openConnection();
        var conta = listarConta(numeroDaConta);
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RegraDeNegocioException("Valor do saque deve ser superior a zero!");
        }

        if (valor.compareTo(conta.getSaldo()) > 0) {
            throw new RegraDeNegocioException("Saldo insuficiente!");
        }

        if (!conta.getEstaAtiva()) {
            throw new RegraDeNegocioException("Conta desativada");
        }

        new ContaDAO(connection).sacar(numeroDaConta, valor);
    }

    public void realizarDeposito(Integer numeroDaConta, BigDecimal valor) {
        Connection connection = this.connection.openConnection();
        var conta = listarConta(numeroDaConta);

        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RegraDeNegocioException("Valor do deposito deve ser superior a zero!");
        }
        if (!conta.getEstaAtiva()) {
            throw new RegraDeNegocioException("Conta desativada");
        }
        new ContaDAO(connection).depositar(conta.getNumero(), valor);
    }

    public void transferirValor(int numeroContaOrigem, int numeroContaDestino, BigDecimal valor) {
        this.realizarSaque(numeroContaOrigem, valor);
        this.realizarDeposito(numeroContaDestino, valor);

    }

    public void encerrar(Integer numeroDaConta) {
        Connection conexao = connection.openConnection();
        var conta = listarConta(numeroDaConta);

        if (conta.getSaldo().compareTo(BigDecimal.ZERO) > 0) {
            throw new RegraDeNegocioException("Conta não pode ser encerrada pois ainda possui saldo!");
        }

        new ContaDAO(conexao).excluirConta(numeroDaConta);
    }

    public void desativarConta(int numeroConta) {
        Connection conexao = connection.openConnection();
        var conta = listarConta(numeroConta);

        new ContaDAO(conexao).desativarConta(conta.getNumero());
    }

    public void reativarConta(int numeroConta) {
        Connection conexao = connection.openConnection();
        var conta = listarConta(numeroConta);

        new ContaDAO(conexao).reativarConta(conta.getNumero());
    }

}



