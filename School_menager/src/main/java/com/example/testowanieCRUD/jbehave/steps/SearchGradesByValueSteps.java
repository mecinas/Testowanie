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
import java.util.List;

@Component
public class SearchGradesByValueSteps {

    public String endpoint;
    public float value;

    public GradeRepository gradeRepository;
    public StudentRepository studentRepository;
    public CourseRepository courseRepository;

    @Autowired(required = false)
    private TestRestTemplate testRestTemplate;

    @Autowired
    private GradeRepository autowiredGradeRepository;
    @Autowired
    private StudentRepository autowiredStudentRepository;
    @Autowired
    private CourseRepository autowiredCourseRepository;

    @Given("grades endpoint")
    public void giveEndpoint() {
        endpoint = "http://localhost:8080/grades/value/";
    }

    @Given("grades repository")
    public void giveRepositoryOfGrades() {
        gradeRepository = autowiredGradeRepository;
    }

    @Given("students repository")
    public void giveRepositoryOfStudents() {
        studentRepository = autowiredStudentRepository;
    }

    @Given("courses repository")
    public void giveRepositoryOfCourses() {
        courseRepository = autowiredCourseRepository;
    }

    @Given("grade value")
    public void giveValue() {
        value = 4.0f;
    }

    @When("in repository there are grades with given value")
    public void whenRepositoryContainsGrades() {
        Student student = new Student("Nowakowski", LocalDate.of(1999, 1, 1));
        Course course = new Course("Testowanie", 4);
        Grade grade = new Grade("2020Z", value, student, course);
        studentRepository.save(student);
        courseRepository.save(course);
        gradeRepository.save(grade);
    }

    @Then("api would respond json of these grades")
    public void apiRespond() {
        ResponseEntity<List<Grade>> postResponse = testRestTemplate.exchange(endpoint + value, HttpMethod.GET,null, new ParameterizedTypeReference<List<Grade>>(){});
        Assertions.assertNotNull(postResponse);
        Assertions.assertEquals(HttpStatus.OK, postResponse.getStatusCode());
        Assertions.assertNotNull(postResponse.getBody());
        Assertions.assertTrue(postResponse.getBody().size() > 0);
        Assertions.assertEquals(4.0f, postResponse.getBody().get(0).getValue());
    }
}
