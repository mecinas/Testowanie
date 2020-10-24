package com.example.testowanieCRUD.controller;

import com.example.testowanieCRUD.entity.Student;
import com.example.testowanieCRUD.repository.StudentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private final StudentRepository repository;

    StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/students")
    public List<Student> all() {
        return repository.findAll();
    }

    @PostMapping("/students/add")
    public Student newStudent(@RequestBody Student newStudent) {
        return repository.save(newStudent);
    }

    @GetMapping("/students/{id}")
    public Student one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    @PutMapping("/students/{id}")
    public Student replaceStudent(@RequestBody Student newStudent, @PathVariable Long id) {
        return repository.findById(id)
                .map(student -> {
                    student.setName(newStudent.getName());
                    student.setDateOfBirth(newStudent.getDateOfBirth());
                    return repository.save(student);
                })
                .orElseGet(() -> {
                    newStudent.setId(id);
                    return repository.save(newStudent);
                });
    }

    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
