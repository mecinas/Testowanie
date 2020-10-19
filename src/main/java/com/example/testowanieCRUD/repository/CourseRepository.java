package com.example.testowanieCRUD.repository;

import com.example.testowanieCRUD.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
}
