
public class StudentInfo implements Comparable<StudentInfo>{
    private final Integer studentID;
    private final String studentFullName;
    private final String studentCourse;
    private final Integer studentGrade;

    public StudentInfo(String[] splitInfo){
        this.studentID =Integer.parseInt(splitInfo[0]);
        this.studentFullName = splitInfo[1];
        this.studentCourse = splitInfo[2];
        this.studentGrade = Integer.parseInt(splitInfo[3]);
    }
    public Integer getStudentID() {
        return studentID;
    }
    public String getStudentFullName() {
        return studentFullName;
    }
    public String getStudentCourse() {
        return studentCourse;
    }
    public Integer getStudentGrade() {
        return studentGrade;
    }
    @Override
    public int compareTo(StudentInfo that) {
        if (this.studentGrade.compareTo(that.studentGrade) == 0) {
            return this.studentFullName.compareTo(that.studentFullName);
        }
        else {
            return that.studentGrade.compareTo(this.studentGrade);
        }
    }
}
