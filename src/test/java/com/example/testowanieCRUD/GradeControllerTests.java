package com.example.testowanieCRUD;

import com.example.testowanieCRUD.entity.Course;
import com.example.testowanieCRUD.entity.Grade;
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
public class GradeControllerTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void testAddGrade() {
        Student sebastian = new Student("Sebastian", LocalDate.of(2011, 1, 2));
        Course rosyjski = new Course("Język Rosyjski", 1);
        Grade grade = new Grade("2012Z", 3.0F, sebastian, rosyjski);

        ResponseEntity<Grade> postResponse = restTemplate.postForEntity(getRootUrl() + "/grades/add", grade, Grade.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        assertEquals(HttpStatus.OK, postResponse.getStatusCode());
    }

    @Test
    public void testGetAllGrades() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/grades",
                HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetGradeById() {
        int id = 16;
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/grades/" + id,
                HttpMethod.GET, entity, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testUpdateGrade() {
        int id = 16;
        Grade grade = restTemplate.getForObject(getRootUrl() + "/grades/" + id, Grade.class);
        grade.setSemester("2017Z");
        restTemplate.put(getRootUrl() + "/grades/" + id, grade);
        Grade updatedGrade = restTemplate.getForObject(getRootUrl() + "/grades/" + id, Grade.class);
        assertNotNull(updatedGrade);
        assertEquals(updatedGrade.getSemester(), "2017Z");
    }

    @Test
    public void testFindGradesByValue() {
        float gradeValue = 5.0F;
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/grades/value/" + gradeValue,
                HttpMethod.GET, entity, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testFindGradesBySemester() {
        String semester = "2012Z";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/grades/semester/" + semester,
                HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testDeleteGrade() {
        int id = 17;
        Grade grade = restTemplate.getForObject(getRootUrl() + "/grades/" + id, Grade.class);
        assertNotNull(grade);
        restTemplate.delete(getRootUrl() + "/grades/" + id);
        try {
            restTemplate.getForObject(getRootUrl() + "/grades/" + id, Grade.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}