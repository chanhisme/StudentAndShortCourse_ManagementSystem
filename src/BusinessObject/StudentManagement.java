/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BusinessObject;

import Entities.Student;
import ObjectData.StudentDAO;
import Utilities.DataInput;

/**
 *
 * @author chanh
 */
public class StudentManagement {

    StudentDAO studentDAO;

    public StudentManagement(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public void processMenuForStudent() {
        
    }

    public Student inputStudent() throws Exception {
        String id = DataInput.getString("Enter the id:");
        String name = DataInput.getString("Enter the name:");
        String major = DataInput.getString("Enter the major:");
        double gpa = DataInput.getDoubleNumber("Enter the gpa:");
        return new Student(id, name, major, gpa);
    }

    public void addNewStudent() {
        Student student = null;
        try {
            student = inputStudent();
            if (student == null) {
                throw new Exception("Object must be not null");
            }
            if (findById(student.getId()) != null) {
                throw new Exception("This id is existed");
            }
            studentDAO.add(student);
            studentDAO.save();
            System.out.println("Student added successfully");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Student findById(String id) {
        return studentDAO.findById(id);
    }
}
