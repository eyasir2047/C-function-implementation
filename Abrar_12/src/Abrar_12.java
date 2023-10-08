import java.sql.Connection;
import  java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

//Abrar Eyasir ---> roll 12

public class Abrar_12 {
    public static void main(String[] args) {
       // String url = "jdbc:mysql://10.33.4.30/db2020015622";
        String url       = "jdbc:mysql://localhost/db2020015622";
     //   String username = "s2020015622";
        String username="root";
        String password = "ag20472662";
       // String password="";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(url, username, password);
                Statement stmnt = conn.createStatement();
                String query_select = "select * from tbl_ExamMarks";
                String query_update = "insert into tbl_ExamMarks values(?,?,?,?,?,?)";
                ResultSet result = stmnt.executeQuery(query_select);

                PreparedStatement pstmnt=conn.prepareStatement(query_update);

                Scanner scanner = new Scanner(System.in);
//                String building_name = scanner.nextLine();
//                int room_number= scanner.nextInt();
//                int capacity=scanner.nextInt();


//                pstmnt.setString(1,building_name);
//                pstmnt.setInt(2,room_number);
//                pstmnt.setInt(3,capacity);

                int StudentID=scanner.nextInt();
                String Course=scanner.nextLine();

                String ExamDate = scanner.nextLine();

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date date2 = dateFormat.parse(ExamDate);
                    //System.out.println(dateFormat.format(date));
                } catch (java.text.ParseException e) {
                    System.out.println("Invalid date format. Please enter a date in the format yyyy-MM-dd.");
                }

                double IncourseMarks= scanner.nextDouble();
                double FinalMarks=scanner.nextDouble();
                double  TotalMarks=scanner.nextDouble();

                pstmnt.setInt(1,StudentID);
                pstmnt.setString(2,Course);
              // pstmnt.setDate(3,23-12-2002);

//                Date date = new Date();
//                java.sql.Date sqlDate = new java.sql.Date( date.getTime() );
//                prepare.setString( 7 , String.valueOf( sqlDate ));



                int returned = pstmnt.executeUpdate();



                int num_rows = 0;
                while (result.next()) {
                    System.out.println(result.getString("building") + " " + result.getString(2) + " " + result.getString(3));
                    num_rows++;
                }
                System.out.println("Total number of rows printed: " + num_rows);
                conn.close();
            } catch (SQLException ex) {
               // Logger.getLogger(JDBCdemo.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
        }
        catch (ClassNotFoundException ex) {
            //Logger.getLogger(JDBCdemo.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
}