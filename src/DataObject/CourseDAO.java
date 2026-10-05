/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataObject;

import Entities.Course;
import Utilities.Constants;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author chanh
 */
public class CourseDAO extends BasedDAO<Course> {

    Map<String, Course> courseMap;
    private String separation = ", ";

    public CourseDAO(Map<String, Course> courseMap, String FILE_PATH) {
        super(FILE_PATH);
        this.courseMap = courseMap;
    }

    public boolean isDuplicate(String courseId, String studentId) {
        return findById(courseId, studentId) != null;
    }

    private static String makeKey(String courseId, String studentId) {
        return courseId.trim() + "|" + studentId.trim();
    }

    private static String[] parseKey(String key) {
        return key.split("\\|");
    }

    @Override
    public void add(Course course) {
        courseMap.put(makeKey(course.getCourseId(), course.getStudentId()), course);
    }

    @Override
    public ArrayList<Course> getAll() {
        return new ArrayList<>(courseMap.values());
    }

    public Course findById(String courseId, String studentId) {
        return courseMap.get(makeKey(courseId, studentId));
    }
    
    public ArrayList <Course> findAllCourseByStudentId(String studentId){
        ArrayList <Course> res = new ArrayList<>();
        for(Course c : getAll()){
            if(c.getStudentId().equalsIgnoreCase(studentId)){
                res.add(c);
            }
        }
        return res;
    }
    
    public int getTotalDuration(ArrayList <Course> courses){
        int res = 0;
        for(Course c : courses){
             res += c.getDuration();
        }
        return res;
    }
    public ArrayList<Course> GroupAllCourseByStudent() {
        ArrayList<Course> res = getAll();

        res.sort((c1, c2)
                -> c1.getStudentId().compareTo(c2.getStudentId())
        );

        return res;
    }

    @Override
    public boolean save() {
        try ( BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FILE_PATH), StandardCharsets.UTF_8))) {
            for (Course course : getAll()) {
                writer.write(course.getCourseId() + separation);
                writer.write(course.getStudentId() + separation);
                writer.write(course.getName() + separation);
                writer.write(course.getDuration() + separation);
                writer.write(course.getFormatedDate());
                writer.newLine();
            }
            return true;
        } catch (Exception e) {
            System.err.println("Save failed: " + e.getMessage());
            return false;
        }
    }

    @Override
    public void load() {
        courseMap.clear();
        String line;
        try ( BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(FILE_PATH), StandardCharsets.UTF_8))) {
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                if (line.charAt(0) == '\uFEFF') {
                    line = line.substring(1);
                }
                try {
                    String[] parts = line.split(",\\s*");
                    if (parts.length != 5) {
                        System.out.println("Skip bad line: " + line);
                        continue;
                    }
                    String courseId = parts[0].trim();
                    String studentId = parts[1].trim();
                    String name = parts[2].trim();
                    int duration = Integer.parseInt(parts[3].trim());
                    LocalDate startedDate = LocalDate.parse(parts[4].trim(), Constants.DATE_FORMATTER);
                    add(new Course(courseId, studentId, name, duration, startedDate));
                } catch (Exception ex) {
                    System.out.println("Skip bad line: " + line + " (" + ex.getMessage() + ")");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
