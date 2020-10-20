package com.example.testowanieCRUD.controller;

import com.example.testowanieCRUD.entity.GradeEntity;
import com.example.testowanieCRUD.repository.GradeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GradeController {

    private final GradeRepository repository;

    GradeController(GradeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/grades")
    public List<GradeEntity> all() {
        return repository.findAll();
    }

    @PostMapping("/grades/add")
    public GradeEntity newGrade(@RequestBody GradeEntity newGrade) {
        return repository.save(newGrade);
    }

    @GetMapping("/grades/{id}")
    public GradeEntity one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grade not found"));
    }

    @PutMapping("/grades/{id}")
    public GradeEntity replaceGrade(@RequestBody GradeEntity newGrade, @PathVariable Long id) {
        return repository.findById(id)
                .map(grade -> {
                    grade.setGrade(newGrade.getGrade());
                    grade.setSemester(newGrade.getSemester());
                    return repository.save(grade);
                })
                .orElseGet(() -> {
                    newGrade.setId(id);
                    return repository.save(newGrade);
                });
    }

    @DeleteMapping("/grade/{id}")
    public void deleteGrade(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @GetMapping("/grades/grade/{grade}")
    public List<GradeEntity> findByEcts(@PathVariable float grade) {
        return repository.findByGrade(grade);
    }

    @GetMapping("/grades/semester/{semester}")
    public List<GradeEntity> findByEcts(@PathVariable String semester) {
        return repository.findBySemester(semester);
    }
}

