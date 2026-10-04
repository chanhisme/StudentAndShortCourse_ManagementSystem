package BusinessObject;

import Entities.Student;
import Utilities.DataInput;
import Utilities.Menu;
import DataObject.StudentDAO;
import java.util.Map;
import java.util.LinkedHashMap;
import Entities.Course;
import DataObject.CourseDAO;

/**
 *
 * @author chanh
 */
public class Program {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final String STUDENT_FILE_PATH = "Students.txt";
        final String COURSE_FILE_PATH = "Courses.txt";

        int choice;
        Map<String, Course> courses = new LinkedHashMap<>();
        Map<String, Student> students = new LinkedHashMap<>();

        StudentDAO studentDao = new StudentDAO(students, STUDENT_FILE_PATH);
        CourseDAO courseDAO = new CourseDAO(courses, COURSE_FILE_PATH);

        courseDAO.load();
        studentDao.load();
        StudentManagement studentManagement = new StudentManagement(studentDao);
        CourseManagement courseManagement = new CourseManagement(courseDAO, studentManagement);
        try {

            do {
                System.out.println("\n\n***************Main Menu***************");
                Menu.printMenu("1.Student Management"
                        + "|2.Course Management"
                        + "|3.Save data to files"
                        + "|4.Exit|Select:");
                choice = DataInput.getIntegerNumber();
                switch (choice) {
                    case 1:
                        studentManagement.processMenuForStudent();
                        break;
                    case 2:
                        courseManagement.processMenuForCourse();
                        
                        break;
                    case 3:
                        break;
                    default:
                        System.out.println("Good bye !");
                        System.exit(0);
                        break;
                }
            } while (true);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
