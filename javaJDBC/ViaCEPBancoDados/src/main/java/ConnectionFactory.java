import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public Connection openConnection() {

        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/viaCEP?user=root&password=10062006Dudu");
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
