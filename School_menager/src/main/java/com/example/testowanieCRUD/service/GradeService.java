package com.example.testowanieCRUD.service;

import com.example.testowanieCRUD.entity.Grade;
import com.example.testowanieCRUD.repository.GradeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GradeService {

    private final GradeRepository gradeRepository;

    public GradeService(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    public List<Grade> getFailedGrades() {
        return gradeRepository.findByValue(2f);
    }

    public List<Grade> getPassingGrades() {
        return gradeRepository.findAll().stream().filter(x -> x.getValue() >= 3.0).collect(Collectors.toList());
    }
}
