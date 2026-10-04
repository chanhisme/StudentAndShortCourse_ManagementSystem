package BusinessObject;

import Entities.Student;
import Utilities.DataInput;
import Utilities.Menu;
import DataObject.StudentDAO;
import java.util.Map;
import java.util.LinkedHashMap;
import Entities.Course;
import DataObject.CourseDAO;
import javax.xml.transform.OutputKeys;

/**
 *
 * @author chanh
 */
public class Program {

    /**
     * @param args the command line arguments
     */
    static final String YES = "y";
    static final String NO = "n";

    public static void main(String[] args) {

        final String STUDENT_FILE_PATH = "Students.txt";
        final String COURSE_FILE_PATH = "Courses.txt";

        int choice;
        Map<String, Course> courses = new LinkedHashMap<>();
        Map<String, Student> students = new LinkedHashMap<>();

        StudentDAO studentDAO = new StudentDAO(students, STUDENT_FILE_PATH);
        CourseDAO courseDAO = new CourseDAO(courses, COURSE_FILE_PATH);

        courseDAO.load();
        studentDAO.load();
        StudentManagement studentManagement = new StudentManagement(studentDAO);
        CourseManagement courseManagement = new CourseManagement(courseDAO, studentManagement);
        try {

            do {
                System.out.println("\n\n***************Main Menu***************");
                Menu.printMenu("1.Student Management"
                        + "|2.Course Management"
                        + "|3.Save data to files"
                        + "|4.Quit program|Select:");
                choice = DataInput.getIntegerNumber();
                switch (choice) {
                    case 1:
                        studentManagement.processMenuForStudent();
                        break;
                    case 2:
                        courseManagement.processMenuForCourse();
                        break;
                    case 3:
                        saveAll(courseDAO, studentDAO);
                        break;
                    case 4:
                        exitProcess(courseDAO, studentDAO);
                        break;
                    default:
                        System.out.println("Data invalid");

                }
            } while (true);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void exitProcess(CourseDAO courseDAO, StudentDAO studentDAO) {

        String opt;
        do {
            opt = DataInput.getString("Do you want to save the changes before exiting? (Y/N): ");
            if (opt.equalsIgnoreCase(YES)) {
                saveAll(courseDAO, studentDAO);
                System.out.println("Good bye");
            } else if (opt.equalsIgnoreCase(NO)) {
                return;

            }
            if(!isValidExit(opt)){
                System.out.println("Input not valid");
            }
        } while (!isValidExit(opt));
    }

    private static boolean isValidExit(String s) {
        return s.equalsIgnoreCase(NO) || s.equalsIgnoreCase(YES);
    }

    private static void saveAll(CourseDAO courseDAO, StudentDAO studentDAO) {
        boolean courseOk = courseDAO.save();
        boolean studentOk = studentDAO.save();
        if (courseOk && studentOk) {
            System.out.println("Save all successfully");
        } else {
            System.out.println("Save all failed");
        }
    }

}
