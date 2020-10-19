package com.example.testowanieCRUD.repository;

import com.example.testowanieCRUD.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    List<StudentEntity> findByNameIgnoreCase(String name);

    List<StudentEntity> findByDateOfBirth(LocalDate dateOfBirth);
}
