import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlDbDriver implements DbDriver{

    private Connection connection;

    @Override
    public void connect(String url, String username, String password) {

        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection= DriverManager.getConnection(url, username, password);

        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void executeQuery(String query) {
        try {

            Statement statement=connection.createStatement();
            statement.executeUpdate(query);

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void closeConnection() {
        try {

            if(connection!=null){
                connection.close();
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
