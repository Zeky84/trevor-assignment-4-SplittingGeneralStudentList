import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentCourseService {
    String[] headerLine = new String[4];
    private List<StudentInfo> loadStudents() throws IOException {

        List<StudentInfo> allStudentsInfo = new ArrayList<>();
        try (BufferedReader fileReader = new BufferedReader(new FileReader("student-master-list.csv"))) {
            String line;
            //Reading the header line of the file to avoid the try-catch message
             headerLine = fileReader.readLine().split(",");
            while ((line = fileReader.readLine()) != null) {
                //NOTE
                try {
                    allStudentsInfo.add(new StudentInfo(line.split(",")));
                } catch (NumberFormatException nfe) {
                    System.out.println("NumberFormat Exception: " + nfe +
                            " Because first line of the file contains header," +
                            "header values can't be use to feed POJO attributes!.");
                }
            }
        }
        return allStudentsInfo;
    }

    public void studentGroupsExportCSV() throws IOException {
        //Making list of StudentInfo to store every course
        List<StudentInfo> groupCourse1 = new ArrayList<>();
        List<StudentInfo> groupCourse2 = new ArrayList<>();
        List<StudentInfo> groupCourse3 = new ArrayList<>();

        for (StudentInfo student : loadStudents()) {
            if (student.getStudentCourse().contains("COMPSCI")) {
                groupCourse1.add(student);
            }
            if (student.getStudentCourse().contains("APMTH")) {
                groupCourse2.add(student);
            }
            if (student.getStudentCourse().contains("STAT")) {
                groupCourse3.add(student);
            }
        }
        //Converting List<StudentInfo> to Array StudentInfo to be sort
        StudentInfo[] course1 = groupCourse1.toArray(new StudentInfo[groupCourse1.toArray().length]);
        StudentInfo[] course2 = groupCourse2.toArray(new StudentInfo[groupCourse2.toArray().length]);
        StudentInfo[] course3 = groupCourse3.toArray(new StudentInfo[groupCourse3.toArray().length]);

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
            writer.write(headerLine[0]+","+ headerLine[1]+","+headerLine[2]+","+headerLine[3]+"\n");
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
