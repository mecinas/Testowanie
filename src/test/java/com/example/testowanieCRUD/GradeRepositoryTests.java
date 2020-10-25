package com.example.testowanieCRUD;

import com.example.testowanieCRUD.entity.Course;
import com.example.testowanieCRUD.entity.Grade;
import com.example.testowanieCRUD.entity.Student;
import com.example.testowanieCRUD.repository.GradeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class GradeRepositoryTests {

    @Autowired
    private GradeRepository gradeRepository;

    @Test
    public void testCreateGrade(){
        Student sebastian = new Student("Sebastian", LocalDate.of(2005, 10, 1));
        Course wychowanie = new Course("Wychowanie fizyczne", 1);
        Grade grade = gradeRepository.save(new Grade("2012Z", 3.0F, sebastian, wychowanie));
        assert(grade.getId() > 0);
    }

    @Test
    public void testFindGradeByItsValue(){
        Grade grade = gradeRepository.findByValue(3.0F).get(0);
        assert(grade.getSemester().compareTo("2012Z") == 0);
    }

    @Test
    public void testFindGradeBySemester(){
        Grade grade = gradeRepository.findBySemester("2019Z").get(0);
        assert(Math.abs(grade.getValue() - 4.0F) < 0.0001);
    }

    @Test
    public void testUpdateGrade() {
        Grade grade = gradeRepository.findBySemester("2010Z").get(0);
        assert(Math.abs(grade.getValue() - 5.0F) < 0.0001);
        grade.setValue(4.0F);
        gradeRepository.save(grade);

        Grade gradeAfterUpdate = gradeRepository.findBySemester("2010Z").get(0);
        assert(Math.abs(grade.getValue() - 4.0F) < 0.0001);
    }

    @Test
    public void testRemoveGrade() {
        Grade grade = gradeRepository.findBySemester("2010Z").get(0);
        gradeRepository.delete(grade);

        List<Grade> gradeAfterRemoval = gradeRepository.findBySemester("2010Z");
        assert(gradeAfterRemoval.size() == 0);
    }
}
