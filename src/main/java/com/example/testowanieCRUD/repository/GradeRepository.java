package com.example.testowanieCRUD.repository;

import com.example.testowanieCRUD.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findByGrade(float grade);

    List<Grade> findBySemester(String semester);
}
