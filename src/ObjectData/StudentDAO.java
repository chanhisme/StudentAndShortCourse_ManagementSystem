/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ObjectData;

import Entities.Student;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author chanh
 */
public class StudentDAO extends BasedDAO<Student> {

    private Map<String, Student> students;
    private String separation = ", ";

    public StudentDAO(Map<String, Student> students, String FILE_PATH) {
        super(FILE_PATH);
        this.students = students;
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
        try ( BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Student student : getAll()) {
                writer.write(student.getId() + separation);
                writer.write(student.getName() + separation);
                writer.write(student.getMajor() + separation);
                writer.write(student.getGpa() + separation);
                writer.newLine();
            }
            System.out.println("Save ok");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void load() {
        String line = null;
        try(BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))){
            while( (line = reader.readLine()) != null){
                String [] parts = line.split(separation);
                String id = parts[0];
                String name = parts[1];
                String major = parts[2];
                double gpa = Double.valueOf(parts[3]);
                add(new Student(id, name, major, gpa));
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
