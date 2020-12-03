package com.example.testowanieCRUD.repository;

import com.example.testowanieCRUD.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByName(String name);

    List<Course> findByEcts(int ects);
}
