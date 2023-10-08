import java.sql.*;

public class Abrar_12_DatabaseConnector {

    public static Connection connection;

    public static void setConnection()
    {
//        String url = "jdbc:mysql://10.33.4.30/db2020015622";
//        String username = "s2020015622";
//        String password = "ag2047";

        String url       = "jdbc:mysql://localhost/db2020015622";
        String username = "root";
        String password = "ag20472662";

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");


            connection = DriverManager.getConnection(url, username, password);
        }
        catch(ClassNotFoundException e)
        {
            System.err.println("Driver not found");
            e.printStackTrace();
        }
        catch (SQLException e) {
            System.err.println("Database connection error");
            e.printStackTrace();
        }
    }

    public static void closeConnection()
    {
        try
        {
            connection.close();
            connection=null;
        }
        catch (SQLException e)
        {
            System.err.println("Connection Close error");
            e.printStackTrace();
        }
    }

}
