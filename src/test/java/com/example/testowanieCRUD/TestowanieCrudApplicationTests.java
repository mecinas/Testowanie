package com.example.testowanieCRUD;

import com.example.testowanieCRUD.entity.Course;
import com.example.testowanieCRUD.entity.Grade;
import com.example.testowanieCRUD.entity.Student;
import com.example.testowanieCRUD.repository.StudentRepository;
import com.example.testowanieCRUD.repository.CourseRepository;
import com.example.testowanieCRUD.repository.GradeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TestowanieCrudApplicationTests {

	@Autowired
	private StudentRepository studentRepository;

	@Test
	public void testCreateStudent(){
		Student student = studentRepository.save(new Student("Ala", LocalDate.of(2000, 4, 4)));
		assert(student.getId() > 0);
	}

	@Test
	public void testFindStudentByName(){
		Student student = studentRepository.findByNameIgnoreCase("Damian").get(0);
		assert(student.getName().compareTo("Damian") == 0);
	}

	@Test
	public void testFindStudentByDateOfBirth(){
		Student student = studentRepository.findByDateOfBirth(LocalDate.of(1990, 3, 3)).get(0);
		assert(student.getName().compareTo("Szymon") == 0);
	}



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
		Grade grade = gradeRepository.findByGrade(3.0F).get(0);
		assert(grade.getSemester().compareTo("2012Z") == 0);
	}

	@Test
	public void testFindGradeBySemester(){
		Grade grade = gradeRepository.findBySemester("2019Z").get(0);
		assert(Math.abs(grade.getGrade() - 4.0F) < 0.0001);
	}
}
