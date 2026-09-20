/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ObjectData;

import Entities.Student;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author chanh
 */
public class StudentDAO extends BasedDAO<Student> {
    private Map<String, Student> students;

    public StudentDAO(Map<String, Student> students) {
        super(students);
    }

    @Override
    public void add(Student student) {
        students.put(student.getId(), student);
    }

    @Override
    public ArrayList<Student> getAll() {
        return new ArrayList<>(students.values());
    }

    @Override
    public Student findById(String id) {
        return students.get(id);
    }

    @Override
    public void save() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void load() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
