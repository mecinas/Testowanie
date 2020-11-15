package com.example.testowanieCRUD.service;

import com.example.testowanieCRUD.entity.Course;
import com.example.testowanieCRUD.entity.Grade;
import com.example.testowanieCRUD.entity.Student;
import com.example.testowanieCRUD.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.OptionalDouble;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional
    public int getStudentsECTS(Long id) {
        Student student = studentRepository.findById(id).orElseThrow();
        Optional<Integer> ectsSum = student.getCourses().stream().map(Course::getEcts).reduce(Integer::sum);
        return ectsSum.orElse(0);
    }

    public String checkStudentsECTS(int ects) {
        return ects < 30 ? "Student nie posiada wymaganej liczby ECTS" : "Student posiada wymaganą liczbę ECTS";
    }

    @Transactional
    public double getAverageGrade(Long id) {
        Student student = studentRepository.findById(id).orElseThrow();
        OptionalDouble avgGrade = student.getGrades().stream().mapToDouble(Grade::getValue).average();
        return avgGrade.orElse(Double.NaN);
    }
}
