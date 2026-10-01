package BusinessObject;

import DataObject.CourseDAO;
import Entities.Course;
import Utilities.DataInput;
import Utilities.DataValidation;
import Utilities.Menu;
import java.time.LocalDate;

/**
 *
 * @author chanh
 */
public class CourseManagement {

    private CourseDAO courseDAO;
    private int choice;

    public CourseManagement(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    public void processMenuForCourse() {
        try {

            do {
                System.out.println("\n\n***************Student Menu***************");
                Menu.printMenu("1.List all students|2.Add a new student|"
                        + "3.Search for a student by ID|"
                        + "4.Update a Student's GPA by ID|"
                        + "5.List all Students by Major|0.Exit|Select:");
                choice = DataInput.getIntegerNumber();

                switch (choice) {

                    case 0:
                        System.out.println("Exited Course menu");
                        return;

                }
            } while (true);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Course inputNewCourse() {
        String courseId = DataInput.getString("Enter course id: ");
        String studentId = DataInput.getString("Enter student id: ");
        String courseName = DataInput.getString("Enter course name: ");
        int duration = 0;
        try {
            duration = DataInput.getIntegerNumber("Enter duration: ");

        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        String startedDate = DataInput.getString("Enter started date: ");
        
        return new Course(courseId, studentId, courseName, duration, LocalDate.parse(startedDate));
    }

    public void addCourse() {
        Course course = inputNewCourse();
        if(!DataValidation.checkObjectNull(findCourseById(course.getCourseId()))){
            System.out.println("this course is ");
            return;
        }
    }

    public Course findCourseById(String id) {
        if (id == null) {
            System.out.println("Id cannot null");
            return null;
        }
        return courseDAO.findById(id);

    }
}
