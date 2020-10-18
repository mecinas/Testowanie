package com.example.demo.dao;

import com.example.demo.model.Student;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("studentDAS")
public class StudentDataAccessService implements StudentDAO{

    private static List<Student> dataBase = new ArrayList<>();

    @Override
    public int insertStudent(UUID id, Student student) {
        dataBase.add(new Student(id, student.getName()));
        return 1;
    }

    @Override
    public List<Student> selectAllStudents() {
        return dataBase;
    }

    @Override
    public Optional<Student> selectStudentById(UUID id) {
        return dataBase.stream()
                .filter(student -> student.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deleteStudentById(UUID id) {
        Optional<Student> student = selectStudentById(id);
        if (student.isEmpty())
            return 0;
        dataBase.remove(student.get());
        return 1;
    }

    @Override
    public int updateStudentById(UUID id, Student student) {
        return selectStudentById(id)
                .map(studentToDelete -> {
                    int indexOfStudentToDelete = dataBase.indexOf(studentToDelete);
                    if (indexOfStudentToDelete >= 0) {
                        dataBase.set(indexOfStudentToDelete, new Student(id, student.getName()));
                        return 1;
                    }
                    return 0;
                }).orElse(0);
    }
}
