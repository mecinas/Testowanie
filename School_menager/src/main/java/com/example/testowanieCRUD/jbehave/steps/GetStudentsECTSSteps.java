package com.example.testowanieCRUD.jbehave.steps;

import com.example.testowanieCRUD.entity.Course;
import com.example.testowanieCRUD.entity.Grade;
import com.example.testowanieCRUD.entity.Student;
import com.example.testowanieCRUD.repository.CourseRepository;
import com.example.testowanieCRUD.repository.GradeRepository;
import com.example.testowanieCRUD.repository.StudentRepository;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class GetStudentsECTSSteps {

    public String endpoint;
    public Long studentid;
    public StudentRepository studentRepository;
    public GradeRepository gradeRepository;
    public CourseRepository courseRepository;
    @Autowired(required = false)
    private TestRestTemplate testRestTemplate;
    @Autowired
    private StudentRepository studentRepositoryA;
    @Autowired
    private GradeRepository gradeRepositoryA;
    @Autowired
    private CourseRepository courseRepositoryA;
    private Student student;

    @Given("ects endpoint")
    public void giveEndpoint() {
        endpoint = "http://localhost:8080/students/ects/";
    }

    @Given("ects repository of students")
    public void giveRepositoryOfStudents() {
        studentRepository = studentRepositoryA;
    }

    @Given("ects repository of grades")
    public void giveRepositoryOfGrades() {
        gradeRepository = gradeRepositoryA;
    }

    @Given("ects repository of courses")
    public void giveRepositoryOfCourses() {
        courseRepository = courseRepositoryA;
    }

    @Given("ects student's id")
    public void studentsId() {
        student = new Student("Nowak", LocalDate.of(1976, 1, 1));
        studentRepository.save(student);
        studentid = student.getId();
    }

    @When("student is assigned to some courses")
    public void studentIsAssignedToCourses() {
        Course c1 = new Course("Course 1", 13);
        Course c2 = new Course("Course 2", 3);
        courseRepository.saveAll(Arrays.asList(c1, c2));

        student.getCourses().addAll(Arrays.asList(c1, c2));
        studentRepository.save(student);

        Grade g1 = new Grade("2020L", 4.0F, student, c1);
        Grade g2 = new Grade("2020L", 4.0F, student, c2);
        gradeRepository.saveAll(Arrays.asList(g1, g2));
    }

    @Then("api would respond with json containing student's ECTS")
    public void apiRespond() {
        ResponseEntity<Map<String, String>> postResponse = testRestTemplate.exchange(endpoint + studentid, HttpMethod.GET, null, new ParameterizedTypeReference<Map<String, String>>() {
        });
        Assertions.assertNotNull(postResponse);
        Assertions.assertEquals(HttpStatus.OK, postResponse.getStatusCode());
        Assertions.assertNotNull(postResponse.getBody());
        Assertions.assertTrue(postResponse.getBody().size() > 0);
        Assertions.assertEquals("16", postResponse.getBody().get("ects"));
    }
}
