import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MysqldbDriver mysqldbDriver = new MysqldbDriver();

        Scanner scanner = new Scanner(System.in);
        System.out.println("What do you want to do?");
        System.out.println("Press 1 to insert new data");
        System.out.println("Press 2 to find examination data of a student");
        System.out.println("Press 3 to find the maximum and average obtained number in a course on a particular examination date");
        System.out.println("Press 4 to find the list of students who did not attempt for more than once in any course");
        int type = scanner.nextInt();

        if(type == 1) {
            Student data = new Student();
            System.out.println("Enter studentID");
            data.setStudentID(scanner.nextInt());
            scanner.nextLine();
            System.out.println("Enter course");
            data.setCourse(scanner.nextLine());
            System.out.println("Enter examDate (YYYY-MM-DD)");
            data.setExamDate(scanner.nextLine());
            System.out.println("Enter incourseMarks");
            data.setIncourseMarks(scanner.nextInt());
            System.out.println("Enter finalMarks");
            data.setFinalMarks(scanner.nextInt());
            System.out.println("Enter totalMarks");
            data.setTotalMarks(scanner.nextInt());

            mysqldbDriver.save(data);
        }
        else if(type == 2) {
            mysqldbDriver.load(scanner.nextInt());
        }
        else if(type == 3) {
            scanner.nextLine();
            mysqldbDriver.load(scanner.nextLine());
        }
        else if(type == 4) {
            mysqldbDriver.load();
        }

        mysqldbDriver.closeDatabase();
    }
}