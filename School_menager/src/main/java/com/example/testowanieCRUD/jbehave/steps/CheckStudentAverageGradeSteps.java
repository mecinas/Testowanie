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
import java.util.Map;

@Component
public class CheckStudentAverageGradeSteps {

    public String endpoint;
    public long studentId;
    public StudentRepository studentRepository;
    public GradeRepository gradeRepository;
    public CourseRepository courseRepository;
    @Autowired(required = false)
    private TestRestTemplate testRestTemplate;
    @Autowired
    private StudentRepository repository;
    @Autowired
    private GradeRepository grepository;
    @Autowired
    private CourseRepository crepository;
    public Student student;

    @Given("average grade endpoint")
    public void giveEndpoint() {
        endpoint = "/students/checkAverage/";
    }

    @Given("average grade repository of students")
    public void giveRepositoryOfStudents() {
        studentRepository = repository;
    }

    @Given("average grade repository of courses")
    public void giveRepositoryOfCourses() {
        courseRepository = crepository;
    }

    @Given("average grade repository of grades")
    public void giveRepositoryOfGrades() {
        gradeRepository = grepository;
    }

    @Given("average grade student id")
    public void giveStudentId() {
        student = new Student("Nowacki", LocalDate.of(1970, 1, 1));
        studentRepository.save(student);
        studentId = repository.findByNameStartingWith("Nowacki").get(0).getId();
    }

    @When("in repository student has grades")
    public void whenStudentHasGrades() {
        Course course1 = new Course("Kurs avg1", 1);
        Course course2 = new Course("Kurs avg2", 2);
        courseRepository.save(course1);
        courseRepository.save(course2);
        Grade grade1 = new Grade("2020Z", 4.0f, student, course1);
        Grade grade2 = new Grade("2020Z", 3.0f, student, course2);
        gradeRepository.save(grade1);
        gradeRepository.save(grade2);
    }

    @Then("api would respond with json of average grade of student with given id")
    public void apiRespond() {
        ResponseEntity<Map> postResponse = testRestTemplate.exchange(endpoint + studentId, HttpMethod.GET,null, new ParameterizedTypeReference<>(){});
        Assertions.assertNotNull(postResponse);
        Assertions.assertEquals(HttpStatus.OK, postResponse.getStatusCode());
        Assertions.assertNotNull(postResponse.getBody());
        Assertions.assertTrue(postResponse.getBody().size() > 0);
        Assertions.assertTrue(Math.abs((double)postResponse.getBody().get("average") - 3.5) < 0.0001 );
    }
}
