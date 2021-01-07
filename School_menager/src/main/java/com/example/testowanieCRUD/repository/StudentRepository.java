package com.example.testowanieCRUD.repository;

import com.example.testowanieCRUD.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByNameIgnoreCase(String name);

    List<Student> findByDateOfBirth(LocalDate dateOfBirth);


    List<Student> findByNameStartingWith(String startsWith);
}
