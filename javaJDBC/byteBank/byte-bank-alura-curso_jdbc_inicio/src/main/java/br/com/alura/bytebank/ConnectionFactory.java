package br.com.alura.bytebank;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/byte_bank");
        config.setUsername("root");
        config.setPassword("10062006Dudu");
        config.setMaximumPoolSize(10);

        dataSource = new HikariDataSource(config);
    }

    public Connection openConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

//    private HikariDataSource createDataSource(){
//        HikariConfig config = new HikariConfig();
//        config.setJdbcUrl("jdbc:mysql://localhost:3306/byte_bank");
//        config.setUsername("root");
//        config.setPassword("10062006Dudu");
//        config.setMaximumPoolSize(10);
//
//        return new HikariDataSource(config);
//    }


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
