import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/viaCEP");
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


}
