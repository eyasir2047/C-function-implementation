public class Student {
    private int studentID;
    private String course;
    private String examDate;
    private int incourseMarks;
    private int finalMarks;
    private int totalMarks;

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public void setIncourseMarks(int incourseMarks) {
        this.incourseMarks = incourseMarks;
    }

    public void setFinalMarks(int finalMarks) {
        this.finalMarks = finalMarks;
    }

    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getCourse() {
        return course;
    }

    public String getExamDate() {
        return examDate;
    }

    public int getIncourseMarks() {
        return incourseMarks;
    }

    public int getFinalMarks() {
        return finalMarks;
    }

    public int getTotalMarks() {
        return totalMarks;
    }
}

