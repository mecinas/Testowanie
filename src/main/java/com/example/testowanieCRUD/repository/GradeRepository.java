package com.example.testowanieCRUD.repository;

import com.example.testowanieCRUD.entity.GradeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeRepository extends JpaRepository<GradeEntity, Long> {
    List<GradeEntity> findByGrade(float grade);

    List<GradeEntity> findBySemester(String semester);
}
