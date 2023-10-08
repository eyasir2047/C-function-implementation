import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Abrar_12_SQL_QUERY {


    public void printGradeSheet(String ID) {
        Abrar_12_DatabaseConnector.setConnection();

        try {
            PreparedStatement preparedStatement = Abrar_12_DatabaseConnector.connection.prepareStatement("Select StudentID, Course,MAX( TotalMarks) as TotalMarks, Max(ExamDate) as ExamDate from tbl_ExamMarks where StudentID = ? Group By StudentID,Course;");
            preparedStatement.setString(1, ID);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("Student does not have any data");
                return;
            }

            System.out.printf("%-14s %-12s %-15s %-11s%n", "StudentID", "Course", "Obtained Marks", "ExamDate");
            while (resultSet.next()) {
                String stdId = resultSet.getString("StudentID");
                String course = resultSet.getString("Course");

                Double totalMarks = resultSet.getDouble("TotalMarks");

                java.sql.Date sqlDate = resultSet.getDate("ExamDate");
                LocalDate date = sqlDate.toLocalDate();
                System.out.printf("%-14s %-12s %-15s %-11s%n", stdId, course, totalMarks, date);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        Abrar_12_DatabaseConnector.closeConnection();
    }

    public void findMaxAvg(LocalDate date)
    {
        Abrar_12_DatabaseConnector.setConnection();
        Date sqlDate = Date.valueOf(date);

        try
        {
            PreparedStatement preparedStatement = Abrar_12_DatabaseConnector.connection.prepareStatement("Select MAX(TotalMarks) as maxMark , AVG(TotalMarks) as avgMark from tbl_ExamMarks Where ExamDate =? group by Course;");
            preparedStatement.setDate(1,sqlDate);
            ResultSet resultSet =preparedStatement.executeQuery();

            if(!resultSet.isBeforeFirst())
            {
                System.out.println("Date and Course does not have any data");
                return;
            }

            while(resultSet.next())
            {



                Double maxMark = resultSet.getDouble("maxMark");
                Double avgMark = resultSet.getDouble("avgMark");

                System.out.println("Max Mark: "+maxMark);
                System.out.println("Avg Mark: "+avgMark);

            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        Abrar_12_DatabaseConnector.closeConnection();
    }

    public void findAttemptedNotMoreThanOnce(String course)
    {
        Abrar_12_DatabaseConnector.setConnection();
        try {
            PreparedStatement preparedStatement = Abrar_12_DatabaseConnector.connection.prepareStatement("Select Distinct StudentID from (Select StudentID,Course,Count(STudentID) as CountTime from tbl_ExamMarks group by StudentID, Course) as CountTable  where CountTable.CountTime <=1 AND Course =?;");
            preparedStatement.setString(1,course);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("Course does not have any data");
                return;
            }
            System.out.println("List of Students who did not attempt in any course  more than once: ");
            while (resultSet.next()) {
                System.out.print(resultSet.getString("StudentID")+" ");
            }
            System.out.println();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Abrar_12_DatabaseConnector.closeConnection();
    }




    public static void main(String[] args) {

        Abrar_12_SQL_QUERY findInfo = new Abrar_12_SQL_QUERY();


        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Student Id: ");
        String stdId = scanner.nextLine();


        findInfo.printGradeSheet(stdId);

        System.out.print("Enter Exam date (yyyy-MM-dd): ");
        String userInput = scanner.nextLine();


        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            LocalDate localDate = LocalDate.parse(userInput, dateFormat);

            findInfo.findMaxAvg(localDate);
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        System.out.print("Enter courseName to find the list of students who did not attempt more than once " +
                "in any course:");

        String course = scanner.nextLine();

        findInfo.findAttemptedNotMoreThanOnce(course);

        scanner.close();


    }


}
