package com.example.testowanieCRUD;

import com.example.testowanieCRUD.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class StudentControllerTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void testCreateStudent() {
        Student student = new Student("Ala", LocalDate.of(2000, 4, 4));
        ResponseEntity<Student> postResponse = restTemplate.postForEntity(getRootUrl() + "/students/add", student, Student.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        assertEquals(HttpStatus.OK, postResponse.getStatusCode());
    }

    @Test
    public void testGetAllStudents() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/students",
                HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetStudentById() {
        int id = 1;
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/students/" + id,
                HttpMethod.GET, entity, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testUpdateStudent() {
        int id = 1;
        Student student = restTemplate.getForObject(getRootUrl() + "/students/" + id, Student.class);
        student.setName("NameChanged");
        restTemplate.put(getRootUrl() + "/students/" + id, student);
        Student updatedStudent = restTemplate.getForObject(getRootUrl() + "/students/" + id, Student.class);
        assertNotNull(updatedStudent);
        assertEquals(updatedStudent.getName(), "NameChanged");
    }

    @Test
    public void testDeleteStudent() {
        int id = 1;
        Student student = restTemplate.getForObject(getRootUrl() + "/students/" + id, Student.class);
        assertNotNull(student);
        restTemplate.delete(getRootUrl() + "/students/" + id);
        try {
            restTemplate.getForObject(getRootUrl() + "/students/" + id, Student.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}