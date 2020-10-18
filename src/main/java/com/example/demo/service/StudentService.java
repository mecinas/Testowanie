package com.example.demo.service;

import com.example.demo.dao.StudentDAO;
import com.example.demo.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {

    private final StudentDAO studentDAO;

    @Autowired
    public StudentService(@Qualifier("studentDAS") StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public int addStudent(Student student){
        return studentDAO.insertStudent(student);
    }

    public List<Student> getAllPeople(){
        return studentDAO.selectAllStudents();
    }

    public Optional<Student> getStudentById(UUID id){
        return studentDAO.selectStudentById(id);
    }

    public int deleteStudent(UUID id){
        return studentDAO.deleteStudentById(id);
    }

    public int updateStudent(UUID id, Student student){
        return studentDAO.updateStudentById(id, student);
    }
}
