/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilities;

import java.util.Scanner;

/**
 *
 * @author chanh
 */
public class DataInput {

    private static final Scanner sc = new Scanner(System.in);

    public static int getIntegerNumber(String displayMessage)
            throws Exception {
        int number = 0;
        System.out.print(displayMessage);
        number = getIntegerNumber();
        return number;
    }

    //---------------------------------------------------
    public static int getIntegerNumber() throws Exception {
        int number = 0;
        String strInput;
        strInput = getString();
        if (!DataValidation.checkStringWithFormat(strInput, "\\d{1,10}")) {
            throw new Exception("Data invalid.");
        } else {
            number = Integer.parseInt(strInput);
        }
        return number;
    }

    public static double getDoubleNumber(String displayMessage) throws Exception {
        double number = 0;
        String strInput = getString(displayMessage);
        if (DataValidation.checkStringEmpty(strInput)) {
            if (!DataValidation.checkStringWithFormat(strInput, "[0-9]+[.]?[0-9]+")) {
                throw new Exception("Data invalid.");
            } else {
                number = Double.parseDouble(strInput);
            }
        }
        return number;
    }

    public static String getString(String displayMessage) {

        System.out.print(displayMessage);
        String strInput = getString();

        return strInput;
    }

    //---------------------------------------------------
    public static String getString() {

        String strInput = sc.nextLine();

        return strInput.trim();
    }

}
