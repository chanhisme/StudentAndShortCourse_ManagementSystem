package BusinessObject;

import Entities.Student;
import ObjectData.BasedDAO;
import Utilities.DataInput;
import Utilities.Menu;
import ObjectData.StudentDAO;
import java.util.Map;
import java.util.LinkedHashMap;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
/**
 *
 * @author chanh
 */
public class Program {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int choice;
        Map <String, Student> students = new LinkedHashMap<>();
        StudentDAO studentDao = new StudentDAO(students);
                
        StudentManagement studentManagement = new StudentManagement(studentDao);
        try {

            do {
                System.out.println("***************Main Menu***************");
                Menu.printMenu("1.Student Management|2.Course Management|3.Exit|Select:");
                choice = DataInput.getIntegerNumber();
                switch (choice) {
                    case 1:
                       studentManagement.processMenuForStudent();
                        break;
                    case 2:
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
