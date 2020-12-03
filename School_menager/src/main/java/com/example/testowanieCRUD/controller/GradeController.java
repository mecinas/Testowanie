package com.example.testowanieCRUD.controller;

import com.example.testowanieCRUD.entity.Grade;
import com.example.testowanieCRUD.repository.GradeRepository;
import com.example.testowanieCRUD.service.GradeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class GradeController {

    private final GradeRepository repository;
    private final GradeService gradeService;

    GradeController(GradeRepository repository, GradeService gradeService) {
        this.repository = repository;
        this.gradeService = gradeService;
    }

    @GetMapping("/grades")
    public List<Grade> all() {
        return repository.findAll();
    }

    @PostMapping("/grades/add")
    public Grade newGrade(@RequestBody Grade newGrade) {
        return repository.save(newGrade);
    }

    @GetMapping("/grades/{id}")
    public Grade one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grade not found"));
    }

    @PutMapping("/grades/{id}")
    public Grade replaceGrade(@RequestBody Grade newGrade, @PathVariable Long id) {
        return repository.findById(id)
                .map(grade -> {
                    grade.setValue(newGrade.getValue());
                    grade.setSemester(newGrade.getSemester());
                    return repository.save(grade);
                })
                .orElseGet(() -> {
                    newGrade.setId(id);
                    return repository.save(newGrade);
                });
    }

    @DeleteMapping("/grades/{id}")
    public void deleteGrade(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @GetMapping("/grades/value/{value}")
    public List<Grade> findByValue(@PathVariable float value) {
        return repository.findByValue(value);
    }

    @GetMapping("/grades/semester/{semester}")
    public List<Grade> findBySemester(@PathVariable String semester) {
        return repository.findBySemester(semester);
    }

    @GetMapping("/grades/failed")
    public List<Grade> findByIsFailed() {
        return gradeService.getFailedGrades();
    }

    @GetMapping("/grades/passed")
    public List<Grade> findByIsPassed() {
        return gradeService.getPassingGrades();
    }

}

