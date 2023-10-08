public class Marks {
    // akjon student ar akta particular course e marks/score

    private  int mark_id;
    private int studentId;
    private int courseId;

    private double score;

    public Marks(int mark_id,int studentId, int courseId, double score) {
        this.mark_id=mark_id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.score = score;
    }

    public int getMark_id() {
        return mark_id;
    }

    public void setMark_id(int mark_id) {
        this.mark_id = mark_id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Marks{" +
                "marksId=" +mark_id +
                "studentId=" + studentId +
                ", courseId=" + courseId +
                ", score=" + score +
                '}';
    }


}

/*
CREATE TABLE marks (
    marks_id INT PRIMARY KEY,
    student_id INT NOT NULL,
    course_id INT NOT NULL,
    score DECIMAL(5,2) NOT NULL,
    FOREIGN KEY (student_id) REFERENCES students(student_id),
    FOREIGN KEY (course_id) REFERENCES courses(course_id)
);
 */
