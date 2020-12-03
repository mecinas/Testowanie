package com.example.testowanieCRUD;


import com.example.testowanieCRUD.entity.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class CourseControllerTests {
    
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void testCreateCourse(){
        Course course = new Course("Projektowanie GUI", 3);
        ResponseEntity<Course> postResponse = restTemplate.postForEntity(getRootUrl() + "/courses/add", course, Course.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        assertEquals(HttpStatus.OK, postResponse.getStatusCode());
    }

    @Test
    public void testGetAllCourses() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/courses",
                HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetCoursesById() {
        int id = 4;
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/courses/" + id,
                HttpMethod.GET, entity, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testGetCoursesByECTS() {
        int ects = 3;
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/courses/ects/" + ects,
                HttpMethod.GET, entity, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testUpdateCourse() {
        int id = 4;
        Course course = restTemplate.getForObject(getRootUrl() + "/students/" + id, Course.class);
        course.setName("CourseNameChanged");
        restTemplate.put(getRootUrl() + "/courses/" + id, course);
        Course updatedCourse = restTemplate.getForObject(getRootUrl() + "/courses/" + id, Course.class);
        assertNotNull(updatedCourse);
        assertEquals(updatedCourse.getName(), "CourseNameChanged");
    }

    @Test
    public void testDeleteCourse() {
        int id = 5;
        Course course = restTemplate.getForObject(getRootUrl() + "/courses/" + id, Course.class);
        assertNotNull(course);
        restTemplate.delete(getRootUrl() + "/courses/" + id);
        try {
            restTemplate.getForObject(getRootUrl() + "/courses/" + id, Course.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}
