import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentCourseService {
    private ArrayList<StudentInfo> loadStudents() throws IOException {

        ArrayList<StudentInfo> allStudentsInfo = new ArrayList<>();
        try (BufferedReader fileReader = new BufferedReader(new FileReader("student-master-list.csv"))) {
            String line = "";
            while ((line = fileReader.readLine()) != null) {
                try {
                    allStudentsInfo.add(new StudentInfo(line.split(",")));
                } catch (NumberFormatException nfe) {
                    System.out.println("NumberFormat Exception: invalid input string: " + nfe);
                }
            }
        }
        return allStudentsInfo;
    }

    public void studentGroupsExportCSV() throws IOException {
        //Making list of StudentInfo to store every course
        List<StudentInfo> listCourse1 = new ArrayList<>();
        List<StudentInfo> listCourse2 = new ArrayList<>();
        List<StudentInfo> listCourse3 = new ArrayList<>();

        for (StudentInfo student : loadStudents()) {
            if (student.getStudentCourse().contains("COMPSCI")) {
                listCourse1.add(student);
            }
            if (student.getStudentCourse().contains("APMTH")) {
                listCourse2.add(student);
            }
            if (student.getStudentCourse().contains("STAT")) {
                listCourse3.add(student);
            }
        }
        //Converting List<StudentInfo> to Array StudentInfo to be sort
        StudentInfo[] course1 = listCourse1.toArray(new StudentInfo[listCourse1.toArray().length]);
        StudentInfo[] course2 = listCourse2.toArray(new StudentInfo[listCourse2.toArray().length]);
        StudentInfo[] course3 = listCourse3.toArray(new StudentInfo[listCourse3.toArray().length]);

        //Sorting Arrays of every course
        Arrays.sort(course1);
        Arrays.sort(course2);
        Arrays.sort(course3);

        //writing every course to csv file
        writeFileToCSV(course1,"course1.csv");
        writeFileToCSV(course2,"course2.csv");
        writeFileToCSV(course3,"course3.csv");
    }

    private void writeFileToCSV(StudentInfo[] course, String csvFileToExport) throws IOException {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFileToExport))) {
            writer.write("StudentID,Student Name,Course,Grade\n");
            for (StudentInfo student : course) {
                writer.write(student.getStudentID() + "," + student.getStudentFullName() +
                        "," + student.getStudentCourse() + "," + student.getStudentGrade()+"\n");
            }
        }
    }




    //NOTE: Wrote the method below to convert the list<Object> to Array StudentInfo when loadStudents()method was
    // returning list<Object>, changed it later loadStudents() for returning list<StudentInfo> to simplify line of codes
    // and use java methods like .toArray(). QUESTION-> Which way is better? Using java built-in methods or create your
    // own methods.

//    private StudentInfo[] sortingConvertingCourseGroup(List<Object> ArrayListToConvert){
//        /*
//        Return sorted the group of the students converted from ArrayList Objects
//        to an array of StudentInfo.
//         */
//        StudentInfo[] course = new StudentInfo[ArrayListToConvert.toArray().length];
//        int x = 0;
//        for (Object studentObject: ArrayListToConvert){
//            StudentInfo student = (StudentInfo)studentObject;
//            course[x]=student;
//            x++;
//        }
//        Arrays.sort(course);
//        return course;
//    }
}
