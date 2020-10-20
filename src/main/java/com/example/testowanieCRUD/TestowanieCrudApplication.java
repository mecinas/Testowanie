package com.example.testowanieCRUD;

import com.example.testowanieCRUD.entity.CourseEntity;
import com.example.testowanieCRUD.entity.GradeEntity;
import com.example.testowanieCRUD.entity.StudentEntity;
import com.example.testowanieCRUD.repository.CourseRepository;
import com.example.testowanieCRUD.repository.GradeRepository;
import com.example.testowanieCRUD.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Arrays;


@SpringBootApplication
public class TestowanieCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestowanieCrudApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(StudentRepository studentRepository,
                                   CourseRepository courseRepository,
                                   GradeRepository gradeRepository) {

        return args -> {
            studentRepository.save(new StudentEntity("Damian", LocalDate.of(1999, 1, 1)));
            studentRepository.save(new StudentEntity("Jakub", LocalDate.of(1999, 2, 2)));
            StudentEntity szymon = new StudentEntity("Szymon", LocalDate.of(1999, 3, 3));
            studentRepository.save(szymon);

            CourseEntity testowanie = new CourseEntity("Testowanie oprogramowania", 4);
            CourseEntity programowanie = new CourseEntity("Programowanie obiektowe", 5);
            courseRepository.saveAll(Arrays.asList(testowanie, programowanie));

            szymon.getCourses().addAll(Arrays.asList(testowanie, programowanie));
            studentRepository.save(szymon);

            GradeEntity grade = new GradeEntity("2020Z", 4.0F, szymon, testowanie);
            gradeRepository.save(grade);
        };
    }
}