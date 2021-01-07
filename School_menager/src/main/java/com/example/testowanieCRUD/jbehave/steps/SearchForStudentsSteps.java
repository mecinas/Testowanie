package com.example.testowanieCRUD.jbehave.steps;

import com.example.testowanieCRUD.entity.Student;
import com.example.testowanieCRUD.repository.StudentRepository;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Pending;
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

import java.net.URL;
import java.time.LocalDate;
import java.util.List;

@Component
public class SearchForStudentsSteps {

    public String endpoint;
    public String searchPhrase;
    public StudentRepository studentRepository;
    @Autowired(required = false)
    private TestRestTemplate testRestTemplate;
    @Autowired
    private StudentRepository repository;
    private Student student;

    @Given("endpoint")
    public void giveEndpoint() {
        endpoint = "http://localhost:8080/studentsSearch?startsWith=";
    }

    @Given("repository of students")
    public void giveRepositoryOfStudents() {
        studentRepository = repository;
    }

    @Given("search phrase")
    public void giveSearchPhrase() {
        searchPhrase = "Kow";
    }

    @When("in repository there are students whose names start with search phrase")
    public void whenRepositoryContainsStudents() {
        student = new Student("Kowalski", LocalDate.of(1970, 1, 1));
        studentRepository.save(student);
    }

    @Then("api would respond json of these students")
    public void apiRespond() {
        ResponseEntity<List<Student>> postResponse = testRestTemplate.exchange(endpoint + searchPhrase, HttpMethod.GET,null, new ParameterizedTypeReference<List<Student>>(){});
        Assertions.assertNotNull(postResponse);
        Assertions.assertEquals(HttpStatus.OK, postResponse.getStatusCode());
        Assertions.assertNotNull(postResponse.getBody());
        Assertions.assertTrue(postResponse.getBody().size() > 0);
        Assertions.assertTrue(postResponse.getBody().get(0).getName().startsWith(searchPhrase));
    }
}
