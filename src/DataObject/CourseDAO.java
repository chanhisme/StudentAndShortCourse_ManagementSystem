/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataObject;

import Entities.Course;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author chanh
 */
public class CourseDAO extends BasedDAO<Course> {

    Map<String, Course> courseMap;

    public CourseDAO(Map<String, Course> courseMap, String FILE_PATH) {
        super(FILE_PATH);
        this.courseMap = courseMap;
    }

    

    @Override
    public void add(Course course) {
        courseMap.put(course.getCourseId(), course);
    }

    @Override
    public ArrayList<Course> getAll() {
        return new ArrayList<>(courseMap.values());
    }

    @Override
    public Course findById(String id) {
        return courseMap.get(id);
    }

    @Override
    public void save() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void load() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
