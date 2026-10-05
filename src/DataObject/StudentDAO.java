/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataObject;

import Entities.Student;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
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

    public Student findById(String id) {
        return students.get(id);
    }

    @Override
    public boolean save() {
        try ( BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FILE_PATH), StandardCharsets.UTF_8))) {
            for (Student student : getAll()) {
                writer.write(student.getId() + separation);
                writer.write(student.getName() + separation);
                writer.write(student.getMajor() + separation);
                writer.write(String.valueOf(student.getGpa()));
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
                    if (parts.length < 4) {
                        System.out.println("Skip bad line: " + line);
                        continue;
                    }
                    String id = parts[0].trim();
                    String name = parts[1].trim();
                    String major = parts[2].trim();
                    double gpa = Double.parseDouble(parts[3].trim());
                    add(new Student(id, name, major, gpa));
                } catch (Exception ex) {
                    System.out.println("Skip bad line: " + line + " (" + ex.getMessage() + ")");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Student> findStudentByMajor(String major) {
        List<Student> result = new ArrayList<>();
        for (Student student : getAll()) {
            if (student.getMajor().equalsIgnoreCase(major)) {
                result.add(student);
            }
        }
        return result;
    }

    public void removeStudent(String id) {
        students.remove(id);
    }

    public ArrayList<Student> sortAscendingGpa() {
        ArrayList<Student> res = getAll();
        res.sort((s1, s2) -> Double.compare(s1.getGpa(), s2.getGpa()));
        return res;
    }

}
