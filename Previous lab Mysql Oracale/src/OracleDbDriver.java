
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleDbDriver implements DbDriver {
    private Connection connection;

    @Override
    public void connect(String url, String username, String password) {
        try {
            // Load the Oracle JDBC driver class
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Create the connection using the Oracle JDBC URL
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void executeQuery(String query) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
