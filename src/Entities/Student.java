/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import Utilities.DataInput;
import Utilities.DataValidation;

/**
 *
 * @author chanh
 */
public class Student {
    
    private String id;
    private String name;
    private String major;
    private double gpa;
    
    
    private final String REGEX_NAME = "^\\s*\\S+(?:\\s+\\S+)+\\s*$";
    private final String REGEX_ID = "^STU\\d{4}$";
    public Student(String id, String name, String major, double gpa) throws Exception {
        setId(id);
        setName(name);
        setMajor(major);
        setGpa(gpa);

    }

    public String getId() {
        return id;
    }

    public void setId(String id) throws Exception {
        if(!DataValidation.checkStringWithFormat(id, REGEX_ID)){
            throw new Exception("Student ID must follow the format STU0000.");
        }
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws Exception {
        if(!DataValidation.checkStringWithFormat(name, REGEX_NAME)){
            throw new Exception("Name is invalid");
        }
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) throws Exception {
        if(major == null || major.isEmpty()){
            throw new Exception("major must be not null");
        }
        this.major = major;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) throws Exception {
        if(gpa < 0.0 || gpa > 4.0){
            throw new Exception("gpa is invalid");
        }
        this.gpa = gpa;
    }

}
