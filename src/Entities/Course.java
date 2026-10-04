/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

    public Course(String courseId, String studentId, String name, int duration, LocalDate startedDate) {
        this.courseId = courseId;
        this.studentId = studentId;
        this.name = name;
        setDuration(duration);
        this.startedDate = startedDate;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
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

}
