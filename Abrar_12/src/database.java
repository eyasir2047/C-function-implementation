import java.sql.Connection;
import java.sql.DriverManager;

public class database {


    public static Connection connectDb(){

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            //Connect your Database


            String url       = "jdbc:mysql://localhost/project";



            String user = "root";
            //String password = "";


            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/project", "root", "");

            return connect;
        }



        catch (Exception e){
            e.printStackTrace();

        }


        return  null;

    }

}
