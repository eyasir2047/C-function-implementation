import java.sql.*;

public class Main {
        public static void main(String[] args) {

          Abrar_12_DatabaseConnector.setConnection();



            try{

                //create a new book
                String  insertQuery= "INSERT INTO books(title,author,publication_year,isbn) VALUES(?,?,?,?)";

                PreparedStatement insertStatement=Abrar_12_DatabaseConnector.connection.prepareStatement(insertQuery);

                insertStatement.setString(1,"Harry Potter");
                insertStatement.setString(2,"JK Rowling");
                insertStatement.setInt(3,2000);
                insertStatement.setString(4,"12121212121");

                insertStatement.executeUpdate();

                //read a book

                String selectQuery= " Select * from books";

                Statement selectStatement=Abrar_12_DatabaseConnector.connection.createStatement();

                ResultSet resultSet=selectStatement.executeQuery(selectQuery);

                while(resultSet.next()){
                    int id=resultSet.getInt("id");
                    String title=resultSet.getString("title");
                    String author= resultSet.getString("author");
                    int publicationYear= resultSet.getInt("publication_year");
                    String isbn=resultSet.getString("isbn");
                    System.out.println("ID: " + id + ", Title: " + title + ", Author: " + author +
                            ", Year: " + publicationYear + ", ISBN: " + isbn);

                }

                //Update

                String updateQuery= "Update books set title=? where id=?";

                PreparedStatement updateStatement=Abrar_12_DatabaseConnector.connection.prepareStatement(updateQuery);

                updateStatement.setString(1,"48 Laws of Power");
                updateStatement.setInt(2,2);
                updateStatement.executeUpdate();


                //delete a book

                String deleteQuery="delete  from books where id=?";

                PreparedStatement deleteStatement=Abrar_12_DatabaseConnector.connection.prepareStatement(deleteQuery);

                deleteStatement.setInt(1,5);
                deleteStatement.executeUpdate();


                //close the resources

                resultSet.close();
                selectStatement.close();
                insertStatement.close();
                updateStatement.close();
                insertStatement.close();
                deleteStatement.close();
               Abrar_12_DatabaseConnector.closeConnection();



            }catch (Exception e){
                e.printStackTrace();
            }


        }
}

//    CREATE TABLE books (
//        id INT AUTO_INCREMENT PRIMARY KEY,
//        title VARCHAR(255),
//    author VARCHAR(255),
//    publication_year INT,
//    isbn VARCHAR(13)
//);


