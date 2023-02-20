import java.io.IOException;

public class StudentCourseApplication {
    public static void main(String[] args) throws IOException {
        StudentCourseService studentCourseAssignation = new StudentCourseService();
        studentCourseAssignation.studentGroupsExportCSV();
    }
}