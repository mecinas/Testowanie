package com.example.testowanieCRUD;

import com.example.testowanieCRUD.entity.Course;
import com.example.testowanieCRUD.entity.Grade;
import com.example.testowanieCRUD.entity.Student;
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
            Student damian = new Student("Damian", LocalDate.of(1999, 1, 1));
            Student szymon = new Student("Szymon", LocalDate.of(1990, 3, 3));
            Student jakub = new Student("Jakub", LocalDate.of(2010, 2, 2));
            studentRepository.save(damian);
            studentRepository.save(szymon);
            studentRepository.save(jakub);

            Course testowanie = new Course("Testowanie oprogramowania", 4);
            Course programowanie = new Course("Programowanie obiektowe", 5);
            Course ochrona = new Course("Ochrona danych", 3);
            Course angielski = new Course("Język angielski", 1);
            Course modelowanie = new Course("Modelowanie biznesowe", 3);
            Course fizyka = new Course("Fizyka ogólna", 1);
            Course bazy = new Course("Modelowanie baz danych", 3);
            Course azure = new Course("Tworzenie aplikacji Azure", 4);
            Course niemiecki = new Course("Język niemiecki", 3);
            Course grafika = new Course("Grafika komputerowa", 5);
            Course systemy = new Course("Systemy operacyjne", 3);
            Course obwody = new Course("Teoria obwodów i sygnałów", 1);

            courseRepository.saveAll(Arrays.asList(testowanie, programowanie, ochrona, angielski, modelowanie, fizyka, bazy, azure, niemiecki, grafika, systemy, obwody));

            damian.getCourses().addAll(Arrays.asList(testowanie, programowanie, ochrona,
                    angielski, modelowanie, fizyka, bazy, azure, niemiecki, grafika));
            szymon.getCourses().addAll(Arrays.asList(programowanie, ochrona, angielski));
            jakub.getCourses().addAll(Arrays.asList(testowanie, programowanie, ochrona));
            studentRepository.save(damian);
            studentRepository.save(szymon);
            studentRepository.save(jakub);


            Grade dtg = new Grade("2017Z", 5.0F, damian, testowanie); // damianTestowanieGrade = dtg
            Grade dpg = new Grade("2012Z", 3.0F, damian, programowanie);
            Grade sog = new Grade("2019Z", 4.0F, szymon, ochrona);
            Grade sag = new Grade("2018Z", 3.0F, szymon, angielski);
            Grade jtg = new Grade("2010Z", 5.0F, jakub, testowanie);
            Grade jog = new Grade("2015Z", 4.0F, jakub, ochrona);
            Grade stg = new Grade("2019L", 2.0F, szymon, fizyka);
            Grade sng = new Grade("2019L", 2.0F, szymon, niemiecki);
            gradeRepository.save(dtg);
            gradeRepository.save(dpg);
            gradeRepository.save(sog);
            gradeRepository.save(sag);
            gradeRepository.save(jtg);
            gradeRepository.save(jog);
            gradeRepository.save(stg);
            gradeRepository.save(sng);
        };
    }
}