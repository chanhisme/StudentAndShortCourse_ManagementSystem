/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilities;

/**
 *
 * @author chanh
 */
public final class DataValidation {

    public static boolean checkStringWithFormat(String value, String pattern) {
        boolean result = false;
        if (value.matches(pattern)) {
            result = true;
        }
        return result;
    }
    
    public static boolean checkStringEmpty(String value) {
        boolean result = true;
         if(value.isEmpty()){
             result = false;                     
         }
         return result;        
    }
}

