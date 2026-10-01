package Utilities;

import java.time.format.DateTimeFormatter;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author chanh
 */
public class Constants {

    public static final String DATE_PATTERN = "dd/MM/yyyy";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);
    
    
    public static final String REGEX_STUDENT_NAME = "^\\s*\\S+(?:\\s+\\S+)+\\s*$";
    public static final String REGEX_STUDENT_ID = "^STU\\d{4}$";
}
