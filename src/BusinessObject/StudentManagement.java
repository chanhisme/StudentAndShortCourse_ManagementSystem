/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BusinessObject;

import Entities.Student;
import DataObject.StudentDAO;
import Utilities.DataInput;
import Utilities.Menu;
import java.util.List;
import Utilities.DataValidation;
import java.util.ArrayList;

/**
 *
 * @author chanh
 */
public class StudentManagement {

    private StudentDAO studentDAO;
    private int choice;

    public StudentManagement(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public void processMenuForStudent() {
        try {

            do {
                System.out.println("\n\n***************Student Menu***************");
                Menu.printMenu("1.List all students|2.Add a new student|"
                        + "3.Search for a student by ID|"
                        + "4.Update a Student's GPA by ID|"
                        + "5.List all Students by Major|"
                        + "6.Remove student by id|"
                        + "7.Sort by ascending GPA|"
                        + "0.Exit|Select:");
                choice = DataInput.getIntegerNumber();

                switch (choice) {
                    case 1:
                        listAllStudent(studentDAO.getAll());
                        break;
                    case 2:
                        addNewStudent();
                        break;
                    case 3:
                        searchStudentById();
                        break;
                    case 4:
                        updateStudentById();
                        break;
                    case 5:
                        listAllStudentsByMajor();
                        break;
                    case 6:
                        removeStudent();
                        break;
                    case 7:
                        sortAscendingGpa();
                        break;
                    case 0:
                        System.out.println("Exited Student menu");
                        return;

                }
            } while (true);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String inputStudentId() throws Exception {
        String id = DataInput.getString("Enter student id: ");
        if (!DataValidation.checkStringEmpty(id)) {
            throw new Exception("Id must be not null");
        }
        return id;
    }

    public Student inputStudent() throws Exception {
        String id = inputStudentId();
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
        if (DataValidation.isObjectNull(students) || students.isEmpty()) {
            System.out.println("the student list must be not empty and null");
            return;
        }
        System.out.printf("%-10s %-25s %-25s %-10s\n",
                "ID", "Name", "Major", "GPA");
        for (Student student : students) {
            printStudent(student);
        }
    }
    
    public void sortAscendingGpa(){
        ArrayList <Student> res = studentDAO.sortAscendingGpa();
        if(DataValidation.isObjectNull(res)){
            System.out.println("Empty list");
            return;
        }
        listAllStudent(res);
    }
    
    public void printFoundStudent(Student student) {
        if (DataValidation.isObjectNull(student)) {
            System.out.println("Student ID does not exist!");
            return;
        }

        printStudent(student);

    }

    public void printStudent(Student student) {

        System.out.printf(student.toString());
    }

    public void searchStudentById() {
        try {
            String id = inputStudentId();
            Student student = findById(id);
            printFoundStudent(student);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateStudentById() {
        try {
            String id = inputStudentId();
            Student student = findById(id);
            if (DataValidation.isObjectNull(student)) {
                System.out.println("Student must be not null");
                return;
            }
            if (setnewStudent(student)) {
                studentDAO.save();
                System.out.println("Update successfully");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean setnewStudent(Student student) {
        boolean isSuccess = false;
        String oldName = student.getName();
        String oldMajor = student.getMajor();
        double oldGpa = student.getGpa();
        try {
            String newName = DataInput.getString("Enter new name: ");
            String newMajor = DataInput.getString("Enter new major: ");
            double newGpa = DataInput.getDoubleNumber("Enter new gpa: ");
            student.setName(newName);
            student.setMajor(newMajor);
            student.setGpa(newGpa);
            isSuccess = true;
        } catch (Exception e) {
            System.out.println("Update failed: " + e.getMessage());
            try {
                student.setName(oldName);
                student.setMajor(oldMajor);
                student.setGpa(oldGpa);
            } catch (Exception rollBackEx) {
                System.out.println("Roll back failed: " + rollBackEx.getMessage());
            }
        }
        return isSuccess;
    }

    public void listAllStudentsByMajor() {
        String major = DataInput.getString("Enter major: ");
        List<Student> students = studentDAO.findStudentByMajor(major);
        if (DataValidation.isObjectNull(students) || students.isEmpty()) {
            System.out.println("The student list must be not empty");
            return;
        }
        listAllStudent(students);
    }

    public List<Student> findStudentByMajor(String major) {
        return studentDAO.findStudentByMajor(major);
    }

    public Student findById(String id) {
        return studentDAO.findById(id);
    }

    public void removeStudent() {
        try {
            String id = inputStudentId();
            if (DataValidation.isObjectNull(id)) {
                System.out.println("Cannot null");
                return;
            }
            if (DataValidation.isObjectNull(findById(id))) {
                System.out.println("Student not existed");
                return;
            }
            studentDAO.removeStudent(id);
            studentDAO.save();
            System.out.println("Remove successfully");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

}
