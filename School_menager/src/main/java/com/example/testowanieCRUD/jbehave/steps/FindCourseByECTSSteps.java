package com.example.testowanieCRUD.jbehave.steps;

import com.example.testowanieCRUD.entity.Course;
import com.example.testowanieCRUD.entity.Student;
import com.example.testowanieCRUD.repository.CourseRepository;
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
public class FindCourseByECTSSteps {

    public String endpoint;
    public int ECTS;
    public CourseRepository courseRepository;
    @Autowired(required = false)
    private TestRestTemplate testRestTemplate;
    @Autowired
    private CourseRepository repository;
    private Course course;

    @Given("find by ECTS endpoint")
    public void giveEndpoint() {
        endpoint = "http://localhost:8080/courses/ects/";
    }

    @Given("find by ECTS repository of courses")
    public void giveRepositoryOfCourses() {
        courseRepository = repository;
    }

    @Given("find by ECTS number of ECTS")
    public void giveECTS() {
        ECTS = 8;
    }

    @When("in repository there are courses with given number of ECTS")
    public void whenRepositoryContainsCourses() {
        course = new Course("Kurs findbyECTS", 8);
        courseRepository.save(course);
    }

    @Then("api would respond with json of courses with given ECTS")
    public void apiRespond() {
        ResponseEntity<List<Course>> postResponse = testRestTemplate.exchange(endpoint + ECTS, HttpMethod.GET,null, new ParameterizedTypeReference<List<Course>>(){});
        Assertions.assertNotNull(postResponse);
        Assertions.assertEquals(HttpStatus.OK, postResponse.getStatusCode());
        Assertions.assertNotNull(postResponse.getBody());
        Assertions.assertTrue(postResponse.getBody().size() > 0);
        Assertions.assertTrue(postResponse.getBody().get(0).getName().equals("Kurs findbyECTS"));
    }
}
