
package Utilities;

import java.time.LocalDate;

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
        if (value.isEmpty()) {
            result = false;
        }
        return result;
    }

    public static <V> boolean isObjectNull(V object) {
        boolean result = false;

        if (object == null) {
            result = true;
        }

        return result;
    }
    public static boolean isValidStartedDate(LocalDate startedDate){
        
        if (startedDate == null) {
            System.out.println("Start date must be not null");
            return false;
        }
        if (!startedDate.isAfter(LocalDate.now())) {
             System.out.println("Start date must be a future date.");
             return false;
        }
        return true;
    }
    
    public static boolean isPositiveNumber(int number){
        return number >=1;
    }
}
