package com.example.testowanieCRUD;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class TDDGradeLogicTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }


    @Test
    public void testFindFailedGrades() throws JSONException {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/grades/failed",
                HttpMethod.GET, entity, String.class);

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        JSONArray jsonArr = new JSONArray(response.getBody());
        for (int i = 0; i < jsonArr.length(); i++) {
            JSONObject jsonObj = jsonArr.getJSONObject(i);
            assertEquals(2.0, jsonObj.get("value"));
        }
    }

    @Test
    public void testCheckStudentECTS() throws JSONException {
        int id = 1;
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/students/" + id,
                HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        JSONObject jsonObj = new JSONObject(response.getBody());
        JSONArray coursesArray = (JSONArray) jsonObj.get("courses");
        int ectsSum = 0;
        for (int i = 0; i < coursesArray.length(); i++) {
            JSONObject course = coursesArray.getJSONObject(i);
            ectsSum += (int) course.get("ects");
        }

        response = restTemplate.exchange(getRootUrl() + "/students/checkECTS",
                HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        JSONObject ectsJSON = new JSONObject(response.getBody());
        if (ectsSum < 30)
            assertEquals("Student nie posiada wymaganej liczby ECTS", ectsJSON.get("ectsInfo")); // Stworzona funkcja ma JSON z polem ectsInfo
        else
            assertEquals("Student posiada wymaganą liczbę ECTS", ectsJSON.get("ectsInfo"));
    }

    @Test
    public void testAverageGrade() throws JSONException {
        int id = 2;
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/students/" + id,
                HttpMethod.GET, entity, String.class);

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        JSONObject jsonObj = new JSONObject(response.getBody());
        JSONArray gradesArray = (JSONArray) jsonObj.get("grades");
        double gradesValueSum = 0;
        for (int i = 0; i < gradesArray.length(); i++) {
            JSONObject grade = gradesArray.getJSONObject(i);
            gradesValueSum += (double) grade.get("value");
        }
        double average = gradesValueSum / gradesArray.length();

        response = restTemplate.exchange(getRootUrl() + "/students/checkAverage",
                HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        JSONObject averageJSON = new JSONObject(response.getBody());
        assertEquals(average, (double) averageJSON.get("average")); // Wynik zapytania jest JSON i ma pole average
    }
}