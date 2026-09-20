/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BusinessObject;

import Entities.Student;
import ObjectData.StudentDAO;
import Utilities.DataInput;
import Utilities.Menu;
import java.util.List;

/**
 *
 * @author chanh
 */
public class StudentManagement {

    private StudentDAO studentDAO;
    private int choice;
    private String rowFormat = "%-10s %-25s %-25s %-10.2f\n";

    public StudentManagement(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public void processMenuForStudent() {
        try {

            do {
                System.out.println("\n\n***************Student Menu***************");
                Menu.printMenu("1.List all students|2.Add a new student|0.Exit|Select:");
                choice = DataInput.getIntegerNumber();
                switch (choice) {
                    case 1:
                        listAllStudent(studentDAO.getAll());
                        break;
                    case 2:
                        addNewStudent();
                        break;
                    case 0:
                        return;

                }
            } while (true);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Student inputStudent() throws Exception {
        String id = DataInput.getString("Enter the id: ");
        String name = DataInput.getString("Enter the name: ");
        String major = DataInput.getString("Enter the major: ");
        double gpa = DataInput.getDoubleNumber("Enter the gpa: ");
        return new Student(id, name, major, gpa);
    }

    public void addNewStudent() {
        try {
            Student student = inputStudent();
            if (student == null) {
                throw new Exception("Object must be not null");
            }
            if (findById(student.getId()) != null) {
                throw new Exception("This id is existed");
            }

            studentDAO.add(student);
            System.out.println("Student added successfully");

            studentDAO.save();
            System.out.println("Student save successfully");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void listAllStudent(List<Student> students) {
        if (students == null || students.isEmpty()) {
            System.out.println("the student list must be not empty and null");
            return;
        }
        System.out.printf("%-10s %-25s %-25s %-10s\n",
                "ID", "Name", "Major", "GPA");
        for (Student student : students) {
            printStudent(student);
        }
    }

    public void printStudent(Student student) {

        System.out.printf(rowFormat,
                student.getId(),
                student.getName(),
                student.getMajor(),
                student.getGpa());
    }

    public Student findById(String id) {
        return studentDAO.findById(id);
    }
}
