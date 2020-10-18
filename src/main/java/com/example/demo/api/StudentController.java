package com.example.demo.api;

import com.example.demo.dao.StudentDAO;
import com.example.demo.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/student")
@RestController
public class StudentController {

    private final StudentDAO studentDAO;

    @Autowired
    public StudentController(@Qualifier("studentDAS") StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @PostMapping
    public int addStudent(@RequestBody Student student){
        return studentDAO.insertStudent(student);
    }

    @GetMapping
    public List<Student> getAllPeople(){
        return studentDAO.selectAllStudents();
    }

    @GetMapping(path = "{id}")
    public Student getStudent(@PathVariable("id") UUID id){
        return studentDAO.selectStudentById(id)
                .orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public int deleteStudentById(@PathVariable("id") UUID id){
        return studentDAO.deleteStudentById(id);
    }

    @PutMapping(path = "{id}")
    public int updateStudent(@PathVariable("id") UUID id, @RequestBody Student student){
        return studentDAO.updateStudentById(id, student);
    }
}

