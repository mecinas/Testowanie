package com.example.testowanieCRUD;

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

import static org.junit.jupiter.api.Assertions.*;

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

    private ResponseEntity<String> getResponse(String url) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        return restTemplate.exchange(getRootUrl() + url,
                HttpMethod.GET, entity, String.class);
    }

    @Test
    public void testFindFailedGrades() throws JSONException {
        ResponseEntity<String> response = getResponse("/grades/failed");
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
        ResponseEntity<String> response = getResponse("/students/" + id);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        JSONObject jsonObj = new JSONObject(response.getBody());
        JSONArray coursesArray = (JSONArray) jsonObj.get("courses");
        int ectsSum = 0;
        for (int i = 0; i < coursesArray.length(); i++) {
            JSONObject course = coursesArray.getJSONObject(i);
            ectsSum += (int) course.get("ects");
        }
        response = getResponse("/students/checkECTS/" + id);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        JSONObject ectsJSON = new JSONObject(response.getBody());
        if (ectsSum < 30)
            assertEquals("Student nie posiada wymaganej liczby ECTS", ectsJSON.get("ectsInfo")); // Stworzona funkcja ma JSON z polem ectsInfo
        else
            assertEquals("Student posiada wymaganą liczbę ECTS", ectsJSON.get("ectsInfo"));
    }

    @Test
    public void testCheckAverageGrade() throws JSONException {
        int id = 2;
        ResponseEntity<String> response = getResponse("/students/" + id);
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
        response = getResponse("/students/checkAverage/" + id);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        JSONObject averageJSON = new JSONObject(response.getBody());
        assertEquals(average, (double) averageJSON.get("average")); // Wynik zapytania jest JSON i ma pole average
    }

    @Test
    public void testSumAllCoursesEcts() throws JSONException {
        ResponseEntity<String> response = getResponse("/courses/");
        assertEquals(HttpStatus.OK, response.getStatusCode());

        JSONArray courses = new JSONArray(response.getBody());
        int ectsSum = 0;
        for (int i = 0; i < courses.length(); i++) {
            JSONObject course = courses.getJSONObject(i);
            ectsSum += course.getDouble("ects");
        }
        response = getResponse("/courses/ectsSum");
        assertEquals(HttpStatus.OK, response.getStatusCode());

        JSONObject responseEctsSum = new JSONObject(response.getBody());
        assertEquals(ectsSum, responseEctsSum.getInt("sum"));
    }

    @Test
    public void testFindPassedGrades() throws JSONException {
        ResponseEntity<String> response = getResponse("/grades/passed");
        assertEquals(HttpStatus.OK, response.getStatusCode());

        JSONArray grades = new JSONArray(response.getBody());
        for (int i = 0; i < grades.length(); i++) {
            JSONObject grade = grades.getJSONObject(i);
            int id = grade.getInt("id");
            double value = grade.getDouble("value");
            String message = "Value of passed grade must be greater than or equal to 3.0, was: "
                    + value + " for grade id: " + id;
            assertTrue(value >= 3.0, message);
        }
    }
}