package com.example.testowanieCRUD.controller;

import com.example.testowanieCRUD.entity.CourseEntity;
import com.example.testowanieCRUD.repository.CourseRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {

    private final CourseRepository repository;

    CourseController(CourseRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/courses")
    public List<CourseEntity> all() {
        return repository.findAll();
    }

    @PostMapping("/courses/add")
    public CourseEntity newCourse(@RequestBody CourseEntity newCourse) {
        return repository.save(newCourse);
    }

    @GetMapping("/courses/{id}")
    public CourseEntity one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }

    @PutMapping("/courses/{id}")
    public CourseEntity replaceCourse(@RequestBody CourseEntity newCourse, @PathVariable Long id) {

        return repository.findById(id)
                .map(course -> {
                    course.setName(newCourse.getName());
                    course.setEcts(newCourse.getEcts());
                    return repository.save(course);
                })
                .orElseGet(() -> {
                    newCourse.setId(id);
                    return repository.save(newCourse);
                });
    }

    @DeleteMapping("/course/{id}")
    public void deleteCourse(@PathVariable Long id) {
        repository.deleteById(id);
    }
}

