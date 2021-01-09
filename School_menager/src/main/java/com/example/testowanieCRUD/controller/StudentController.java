package com.example.testowanieCRUD.controller;

import com.example.testowanieCRUD.entity.Student;
import com.example.testowanieCRUD.repository.StudentRepository;
import com.example.testowanieCRUD.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class StudentController {

    private final StudentRepository repository;
    private final StudentService studentService;

    StudentController(StudentRepository repository, StudentService studentService) {
        this.repository = repository;
        this.studentService = studentService;
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
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Student not found"
                ));
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

    @GetMapping("/students/checkECTS/{id}")
    public Map<String, String> checkECTS(@PathVariable Long id) {
        int ects = studentService.getStudentsECTS(id);
        String response = studentService.checkStudentsECTS(ects);
        return Collections.singletonMap("ectsInfo", response);
    }

    @GetMapping("/students/ects/{id}")
    public Map<String, String> checkStudentECTS(@PathVariable Long id) {
        int ects = studentService.getStudentsECTS(id);
        return Collections.singletonMap("ects", ects + "");
    }

    @GetMapping("/students/checkAverage/{id}")
    public Map<String, Double> checkAverageGrade(@PathVariable Long id) {
        double avg = studentService.getAverageGrade(id);
        return Collections.singletonMap("average", avg);
    }

    @GetMapping("/studentsSearch")
    public List<Student> checkAverageGrade(@RequestParam String startsWith) {
        List<Student> result = repository.findByNameStartingWith(startsWith);
        if(result.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found");
        return result;
    }
}

