package com.example.testowanieCRUD;

import com.example.testowanieCRUD.entity.StudentEntity;
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
    CommandLineRunner initDatabase(StudentRepository repository) {

        return args -> {
            repository.save(new StudentEntity("Damian"));
            repository.save(new StudentEntity("Szymon"));
            repository.save(new StudentEntity("Jakub"));
        };
    }
}

