package com.example.testowanieCRUD;

import com.example.testowanieCRUD.entity.Course;
import com.example.testowanieCRUD.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class CourseRepositoryTests {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void testCreateCourse(){
        Course course = courseRepository.save(new Course("Modelowanie", 1));
        assert(course.getId() > 0);
    }

    @Test
    public void testFindCourseByName(){
        Course course = courseRepository.findByName("Ochrona danych").get(0);
        assert(course.getName().compareTo("Ochrona danych") == 0);
    }

    @Test
    public void testFindCourseByEcts(){
        Course course = courseRepository.findByEcts(4).get(0);
        assert(course.getName().compareTo("Testowanie oprogramowania") == 0);
    }

    @Test
    public void testUpdateCourse() {
        Course course = courseRepository.findByName("Testowanie oprogramowania").get(0);
        assert(course.getEcts() == 4);
        course.setEcts(6);
        courseRepository.save(course);

        Course courseAfterUpdate = courseRepository.findByName("Testowanie oprogramowania").get(0);
        assert(courseAfterUpdate.getEcts() == 6);
    }
}
