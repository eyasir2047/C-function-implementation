public class Exam {
    private int examId;
    private String examType;// final/ Incourse

    private int courseId;

    public Exam(int examId, String examType, int courseId) {
        this.examId = examId;
        this.examType = examType;
        this.courseId = courseId;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "examId=" + examId +
                ", examType='" + examType + '\'' +
                ", courseId=" + courseId +
                '}';
    }
}
