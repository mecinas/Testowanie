package com.example.testowanieCRUD;

import com.example.testowanieCRUD.entity.CourseEntity;
import com.example.testowanieCRUD.entity.StudentEntity;
import com.example.testowanieCRUD.repository.CourseRepository;
import com.example.testowanieCRUD.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TestowanieCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestowanieCrudApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(StudentRepository studentRepository, CourseRepository courseRepository) {

        return args -> {
            studentRepository.save(new StudentEntity("Damian"));
            studentRepository.save(new StudentEntity("Jakub"));

            CourseEntity testowanie = new CourseEntity("Testowanie oprogramowania", 4);
            courseRepository.save(testowanie);

            StudentEntity szymon = new StudentEntity("Szymon");
            studentRepository.save(szymon);

            szymon.getCourses().add(testowanie);
            studentRepository.save(szymon);
        };
    }
}

