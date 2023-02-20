import java.io.IOException;
import java.util.List;

public class StudentCourseApplication {
    public static void main(String[] args) throws IOException {
        StudentCourseService studentCourseAssignation = new StudentCourseService();
        studentCourseAssignation.studentGroupsExportCSV();
    }
}