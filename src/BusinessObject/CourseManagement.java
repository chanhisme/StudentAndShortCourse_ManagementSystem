package BusinessObject;

import DataObject.CourseDAO;
import Entities.Course;
import Utilities.Constants;
import Utilities.DataInput;
import Utilities.DataValidation;
import Utilities.Menu;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import Entities.Student;

/**
 *
 * @author chanh
 */
public class CourseManagement {

    private final CourseDAO courseDAO;
    private final StudentManagement studentManagement;
    private int choice;

    public CourseManagement(CourseDAO courseDAO, StudentManagement studentManagement) {
        this.courseDAO = courseDAO;
        this.studentManagement = studentManagement;

    }

    public void processMenuForCourse() {
        try {

            do {
                System.out.println("\n\n***************Course Menu***************");
                Menu.printMenu("1.List all course by student|2.Add a new course|"
                        + "3.Calculate total study duration by student ID|0.Exit|Select:");
                choice = DataInput.getIntegerNumber();

                switch (choice) {
                    case 1:
                        listCourseByStudent();
                        break;
                    case 2:
                        addCourse();
                        break;
                    case 3:
                        calculateTotalDuration();
                        break;
                    case 0:
                        System.out.println("Exited Course menu");
                        return;
                    default:
                        System.out.println("Data invalid");

                }
            } while (true);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Course inputNewCourse() throws Exception {
        String courseId = DataInput.getString("Enter course id: ");
        String studentId = studentManagement.inputStudentId();
        String courseName = DataInput.getString("Enter course name: ");
        int duration = 0;
        try {
            duration = DataInput.getIntegerNumber("Enter duration: ");

        } catch (Exception e) {
            throw new Exception("Must be positive");
        }
        String startedDateStr = DataInput.getString("Enter started date (dd/MM/yyyy): ");

        LocalDate startedDate;
        try {
            startedDate = LocalDate.parse(startedDateStr.trim(), Constants.DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new Exception("Start date must be dd/MM/yyyy, e.g. 15/09/2025.");
        }
        if (!startedDate.isAfter(LocalDate.now())) {
            throw new Exception("Start date must be a future date.");
        }

        return new Course(courseId, studentId, courseName, duration, startedDate);
    }

    public void addCourse() {
        try {
            Course course = inputNewCourse();
            if (courseDAO.isDuplicate(course.getCourseId(), course.getStudentId())) {
                System.out.println("Student " + course.getStudentId()
                        + " has already registered course " + course.getCourseId());
                return;
            }
            if (!DataValidation.isValidStartedDate(course.getStartedDate())) {
                System.out.println("Duration must be a positive integer in weeks, minimum 1 week.");
            }
            courseDAO.add(course);
            if (courseDAO.save()) {
                System.out.println("Course added successfully");
            } else {
                System.out.println("Course save failed");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean isDuplicate(String courseId, String studentId) {
        return courseDAO.isDuplicate(courseId, studentId);
    }

    public Course findCourseById(String courseId, String studentId) {
        if (DataValidation.isObjectNull(courseId) || DataValidation.isObjectNull(studentId)) {
            System.out.println("Id cannot null");
            return null;
        }

        return courseDAO.findById(courseId, studentId);

    }

    public void printCourse(Course course) {
        if (DataValidation.isObjectNull(course)) {
            System.out.println("cannot null");
            return;
        }
        System.out.println(course.toString());
    }

    public void printAllCourseGroupByStudent(ArrayList<Course> courses) {
        if (DataValidation.isObjectNull(courses) || courses.isEmpty()) {
            System.out.println("List is empty");
            return;
        }
        String tmp = null;
        for (Course c : courses) {
            Student s = studentManagement.findById(c.getStudentId());
            if (DataValidation.isObjectNull(s)) {
                continue;
            }
            if (tmp == null || !s.getId().equals(tmp)) {
                tmp = s.getId();
                System.out.printf("\n%s, %s, %s\n", s.getId(), s.getName(), s.getMajor());
                System.out.printf("%-10s %-10s %-12s\n", "Course ID", "Duration", "Start Date");
            }
            printCourse(c);
        }
    }

    public void listCourseByStudent() {
        ArrayList<Course> res = courseDAO.GroupAllCourseByStudent();
        if (DataValidation.isObjectNull(res)) {
            System.out.println("Cannot find any");
            return;
        }
        printAllCourseGroupByStudent(res);

    }

    public void calculateTotalDuration() {
        String studentId;
        try {
            studentId = studentManagement.inputStudentId();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        if (DataValidation.isObjectNull(studentManagement.findById(studentId))) {
            System.out.println("id not existed");
            return;
        }

        ArrayList<Course> res = courseDAO.findAllCourseByStudentId(studentId);
        if (DataValidation.isObjectNull(res)) {
            System.out.println("List is empty()");
            return;
        }
        int total = courseDAO.getTotalDuration(res);
        studentManagement.printStudent(studentManagement.findById(studentId));
        System.out.println("Total duration: " + total + " weeks");

    }

}
