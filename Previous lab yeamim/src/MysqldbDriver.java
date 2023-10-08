
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MysqldbDriver extends dbDriver {

    private Connection connection;

    public MysqldbDriver() {
//        String url = "jdbc:mysql://10.33.4.30/db2020215620";
////        String username = "s2020215620";
////        String password = "s2020215620";

        String url = "jdbc:mysql://localhost/db2020015622";
        String username = "root";
        String password = "ag20472662";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connected successfully");
            
        } catch (Exception e) {
            System.out.println("Database connection failed");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void load(int studentID) {
        try {
            String selectQuery = "SELECT * FROM tbl_ExamMarks WHERE StudentID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

            System.out.println("Enter the studentID");
            preparedStatement.setInt(1, studentID);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + " " +
                        resultSet.getString(2) + " " +
                        resultSet.getString(3) + " " +
                        resultSet.getInt(4) + " " +
                        resultSet.getInt(5) + " " +
                        resultSet.getInt(6));
            }
            System.out.println("Data loaded successfully");
        } catch (Exception e) {
            System.out.println("Data load failed");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void load(String examDate) {
        try {
            String selectQuery = "SELECT Course, max(TotalMarks), avg(TotalMarks) FROM tbl_ExamMarks  WHERE ExamDate = ? group by Course";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

            System.out.println("Enter the examDate");
            preparedStatement.setString(1, examDate);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + " " +
                        resultSet.getInt(2) + " " +
                        resultSet.getInt(3));
            }
            System.out.println("Data loaded successfully");
        } catch (Exception e) {
            System.out.println("Data load failed");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void load() {
        try {
            String selectQuery = "SELECT StudentID FROM tbl_ExamMarks GROUP BY StudentID HAVING COUNT(DISTINCT Courses) = COUNT(*)";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void save(Student data) {
        try {
            String selectQuery = "SELECT * FROM tbl_ExamMarks WHERE StudentID = ? AND Course = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, data.getStudentID());
            preparedStatement.setString(2, data.getCourse());

            ResultSet resultSet = preparedStatement.executeQuery();


            ArrayList<Student> list = new ArrayList<>();
            while (resultSet.next()) {
                Student student = new Student();
                student.setStudentID(resultSet.getInt(1));
                student.setCourse(resultSet.getString(2));
                student.setExamDate(resultSet.getString(3));
                student.setIncourseMarks(resultSet.getInt(4));
                student.setFinalMarks(resultSet.getInt(5));
                student.setTotalMarks(resultSet.getInt(6));
                list.add(student);
            }

            if (check(list, data)) {
                String insertQuery = "INSERT INTO tbl_ExamMarks VALUES(?, ?, ?, ?, ?, ?)";
                preparedStatement = connection.prepareStatement(insertQuery);

                preparedStatement.setInt(1, data.getStudentID());
                preparedStatement.setString(2, data.getCourse());
                preparedStatement.setString(3, data.getExamDate());
                preparedStatement.setInt(4, data.getIncourseMarks());
                preparedStatement.setInt(5, data.getFinalMarks());
                preparedStatement.setInt(6, data.getTotalMarks());

                preparedStatement.executeUpdate();
                System.out.println("Data inserted successfully");
            } else {
                System.out.println("Data insertion failed");
            }

            preparedStatement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void closeDatabase() {
        try {
            connection.close();
            System.out.println("Database closed successfully");
        } catch (Exception e) {
            System.out.println("Database close failed");
            System.out.println(e.getMessage());
        }
    }

    private static boolean check(ArrayList<Student> list, Student data) {
        if (list.isEmpty()) {
            //nothing to check
            return true;
        }
        if (list.size() > 2) {
            System.out.println("The student has already sat for 3 times for the exam of this course");
            return false;
        }

        for (Student e : list) {
            String prevDate = e.getExamDate();
            String currDate = data.getExamDate();

            if (convertDateToTime(prevDate) >= convertDateToTime(currDate)) {
                System.out.println(convertDateToTime(prevDate));
                System.out.println(convertDateToTime(currDate));
                System.out.println("Date can't be earlier than the date of any previous exam of this course that the student has sat for");
                return false;
            }
        }

        if (list.get(0).getIncourseMarks() != data.getIncourseMarks()) {
            System.out.println("The incourse marks can't be different");
            return false;
        }

        for (Student e : list) {
            int obtainedMarks = e.getTotalMarks();
            if (obtainedMarks >= 80) {
                System.out.println("The student already got more than or equal to 80% marks in this course");
                return false;
            }
        }

        if (data.getIncourseMarks() + data.getFinalMarks() != data.getTotalMarks()) {
            System.out.println("Enter marks correctly");
            return false;
        }

        return true;
    }

    private static int convertDateToTime(String date) {
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(5, 7));
        int day = Integer.parseInt(date.substring(8, 10));

        return year * 10000 + month * 100 + day;
    }
}
