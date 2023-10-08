
import java.sql.PreparedStatement;

public class Main {

  // to create instances
  public static void main(String[] args) {

    DbDriver mysqlDriver = new MysqlDbDriver();

    String url = "jdbc:mysql://localhost/db2020015622";
    String username = "root";
    String password = "ag20472662";

    mysqlDriver.connect(url, username, password);

    // Insert a student record
    Student student = new Student(15, "Abrar Eyasir");

    mysqlDriver.executeQuery("INSERT INTO students (student_id, name) VALUES ("
            + student.getStudentId() +
            ", '"
            + student.getName() +
            "')");

    // Insert a course record

    Course course = new Course(12, "Database Management");

    mysqlDriver.executeQuery("INSERT INTO courses (course_id, course_name) VALUES ("
            + course.getCourseId() +
            ", '"
            + course.getCourseName() +
            "')");

    // Insert a marks record

    Marks marks = new Marks(34, 35, 25, 96.5);

    mysqlDriver.executeQuery("INSERT INTO marks (marks_id,student_id, course_id, score) VALUES ("
            + marks.getMark_id() +
            ", "
            + marks.getStudentId() +
            ", "
            + marks.getCourseId() +
            ", "
            + marks.getScore() +
            ")");

    mysqlDriver.closeConnection();
  }
}
