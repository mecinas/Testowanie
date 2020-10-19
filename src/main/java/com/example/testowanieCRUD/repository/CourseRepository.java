package com.example.testowanieCRUD.repository;

import com.example.testowanieCRUD.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
    List<CourseEntity> findByName(String name);

    List<CourseEntity> findByEcts(int ects);
}
