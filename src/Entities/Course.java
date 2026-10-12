package Entities;

import java.time.LocalDate;
import Utilities.Constants;
import Utilities.DataValidation;

/**
 *
 * @author chanh
 */
public class Course {

    private String rowFormat = "%-10s %-10d %-12s";

    private String courseId, studentId, name;
    private int duration;
    private LocalDate startedDate;

    public Course(String courseId, String studentId, String name, int duration, LocalDate startedDate) throws Exception {
        setCourseId(courseId);
        setStudentId(studentId);
        this.name = name;
        setDuration(duration);
        this.startedDate = startedDate;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) throws Exception {
        if (courseId == null) {
            throw new Exception("Course ID must be not null.");
        }
        String normalizedId = courseId.trim().toUpperCase();
        if (normalizedId.isEmpty()) {
            throw new Exception("Course ID must be not null.");
        }
        this.courseId = normalizedId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) throws Exception {
        if (studentId == null) {
            throw new Exception("Student ID must be not null.");
        }
        String normalizedId = studentId.trim().toUpperCase();
        if (normalizedId.isEmpty()) {
            throw new Exception("Student ID must be not null.");
        }
        this.studentId = normalizedId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        if(!DataValidation.isPositiveNumber(duration)){
            System.out.println("must be positive number");
            return;
        }
        this.duration = duration;
    }

    public LocalDate getStartedDate() {
        return startedDate;
    }

    public String getFormatedDate() {
        return startedDate.format(Constants.DATE_FORMATTER);
    }

    public void setStartedDate(LocalDate startedDate){

        this.startedDate = startedDate;
    }

    @Override
    public String toString() {
        return String.format(rowFormat,
                getCourseId(),
                getDuration(),
                getFormatedDate()); // dd/MM/yyyy
    }
    
    public String getEndDate(){
        LocalDate endDate = startedDate.plusWeeks(getDuration());
        String res = endDate.format(Constants.DATE_FORMATTER);
        return res;
    }
    
    

}
