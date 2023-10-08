import com.mysql.cj.conf.ConnectionUrlParser;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.ArrayList;
import  java.util.*;

public class Abrar_12_Defining_constraint {


    public static int countAttempt(String sID, String course) {
        try{
            Abrar_12_DatabaseConnector.setConnection();
            String queryAttempts = "select count(*) from tbl_ExamMarks where StudentID=? and Course=?;";

            //attempt check
            PreparedStatement attemptsSt = Abrar_12_DatabaseConnector.connection.prepareStatement(queryAttempts);

            attemptsSt.setString(1,sID);
            attemptsSt.setString(2,course);
            ResultSet attemptsRes = attemptsSt.executeQuery();

            if(attemptsRes==null) {
                System.out.println("attempt count query error");
                return 4;
            }
            attemptsRes.next();
            int ans= attemptsRes.getInt("Count(*)");
            Abrar_12_DatabaseConnector.closeConnection();

            return ans;


        } catch (SQLException e) {
           e.printStackTrace();
        }
        return 4;
    }
    public void insertInto(String StudentID , String Course, LocalDate ExamDate, Double IncourseMarks, Double FinalMarks,Double TotalMarks) {

        if( IncourseMarks > 30 || FinalMarks > 70 || TotalMarks >100) {
            System.out.println("Invalid Marks - Incourse Mark should be <= 30, Final Mark should be <= 70 " +
                    "TotalMarks should be less than 100");
            return;
        }

        try {
            Abrar_12_DatabaseConnector.setConnection();


            PreparedStatement preparedStatement = Abrar_12_DatabaseConnector.connection.prepareStatement(
                    "SELECT COUNT(*)  FROM tbl_ExamMarks WHERE StudentID = ? AND Course = ? AND TotalMarks>=80;");

            preparedStatement.setString(1, StudentID);
            preparedStatement.setString(2, Course);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();



            if (resultSet.getInt(1) != 0) {
                System.out.println("You can't insert here as TotalMarks >= 80 already exist for this student and course");
                return;
            }

            // Insert the new exam marks
            PreparedStatement insertQuery = Abrar_12_DatabaseConnector.connection.prepareStatement(
                    "INSERT INTO tbl_ExamMarks(StudentID, Course, ExamDate, IncourseMarks, FinalMarks,TotalMarks) VALUES (?, ?, ?, ?, ?,?);");
            insertQuery.setString(1, StudentID);
            insertQuery.setString(2, Course);
            insertQuery.setDate(3, java.sql.Date.valueOf(ExamDate));
            insertQuery.setDouble(4, IncourseMarks);
            insertQuery.setDouble(5, FinalMarks);
            insertQuery.setDouble(6,TotalMarks);
            insertQuery.executeUpdate();

            System.out.println("Successfully inserted student exam marks information into the tbl_ExamMarks Database");





            Abrar_12_DatabaseConnector.closeConnection();

        }
        catch (SQLException e) {
            System.out.println("Error!!!!!");
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        Abrar_12_Defining_constraint  insertTask = new Abrar_12_Defining_constraint();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Student Id: ");
        String StudentId = scanner.nextLine();

        System.out.print("Enter Student Course: ");
        String course = scanner.nextLine();

        System.out.print("Enter Exam date (yyyy-MM-dd): ");
        String userInput = scanner.nextLine();

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            LocalDate localDate = LocalDate.parse(userInput, dateFormat);

            System.out.print("Enter Incourse Marks: ");
            Double incourseMark = scanner.nextDouble();

            System.out.print("Enter Final Marks: ");
            Double finalMark = scanner.nextDouble();

            System.out.print("Enter Total Marks: ");
            Double TotalMarks=scanner.nextDouble();

           boolean ans;

           if(countAttempt(StudentId,course)>3){
               ans=false;
           }
            else{
                ans=true;
           }

            if(ans) {

                insertTask.insertInto(StudentId, course, localDate, incourseMark, finalMark, TotalMarks);
            }
            else{
                System.out.println("You have already attempted this exam Thrice, we can't try more than 3 times. Sorry!!");
            }

        }
        catch (Exception e) {
            System.out.println("Error!!!!!");
            e.printStackTrace();
        }
        finally {
            scanner.close();
        }
    }
}
