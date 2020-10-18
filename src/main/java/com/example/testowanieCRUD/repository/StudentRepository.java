package com.example.testowanieCRUD.repository;

import com.example.testowanieCRUD.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
}
