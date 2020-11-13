package com.example.testowanieCRUD.controller;

import com.example.testowanieCRUD.entity.Course;
import com.example.testowanieCRUD.repository.CourseRepository;
import com.example.testowanieCRUD.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class CourseController {

    private final CourseRepository repository;
    private final CourseService courseService;

    CourseController(CourseRepository repository, CourseService courseService) {
        this.repository = repository;
        this.courseService = courseService;
    }

    @GetMapping("/courses")
    public List<Course> all() {
        return repository.findAll();
    }

    @PostMapping("/courses/add")
    public Course newCourse(@RequestBody Course newCourse) {
        return repository.save(newCourse);
    }

    @GetMapping("/courses/{id}")
    public Course one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }

    @PutMapping("/courses/{id}")
    public Course replaceCourse(@RequestBody Course newCourse, @PathVariable Long id) {
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

    @GetMapping("/courses/ectsSum")
    public Map<String, String> getECTSSum() {
        int ectsSum = courseService.getECTSSum();
        return Collections.singletonMap("sum", ectsSum + "");
    }

    @DeleteMapping("/courses/{id}")
    public void deleteCourse(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @GetMapping("/courses/ects/{ects}")
    public List<Course> findByEcts(@PathVariable int ects) {
        return repository.findByEcts(ects);
    }
}

