package com.example.testowanieCRUD.service;

import com.example.testowanieCRUD.entity.Grade;
import com.example.testowanieCRUD.repository.GradeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {

    private final GradeRepository gradeRepository;

    public GradeService(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    public List<Grade> getFailed() {
        return gradeRepository.findByValue(2f);
    }
}
