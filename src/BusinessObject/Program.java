package BusinessObject;

import Utilities.DataInput;
import Utilities.Menu;

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
        try {

            do {
                System.out.println("***************Main Menu***************");
                Menu.printMenu("1.Student Management|2.Course Management|3.Exit|Select:");
                choice = DataInput.getIntegerNumber();
                switch (choice) {
                    case 1:
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
