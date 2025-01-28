package br.com.alura.bytebank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public Connection openConnection() {
        try {
            return DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/byte_bank?user=root&password=10062006Dudu");
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    public static void main(String[] args) {
        // primeiro passa o tipo de conexão com o banco, depois o nome, o host, a porta e no fim o database
        // fazendo a conexão com o banco
        try {
            // Connection serve para guardar a variavel de conexao do conexão MySQL
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/byte_bank?user=root&password=10062006Dudu");
            System.out.println("Conexão bem sucedida");
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
