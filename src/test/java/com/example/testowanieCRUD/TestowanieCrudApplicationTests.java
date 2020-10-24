package com.example.testowanieCRUD;

import com.example.testowanieCRUD.entity.Course;
import com.example.testowanieCRUD.entity.Grade;
import com.example.testowanieCRUD.entity.Student;
import com.example.testowanieCRUD.repository.CourseRepository;
import com.example.testowanieCRUD.repository.GradeRepository;
import com.example.testowanieCRUD.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

	@Test
	public void testFindStudentById(){
		Optional<Student> student = studentRepository.findById(3L);
		assert(student.isPresent() && (student.get().getId() == 3L));
	}

	@Test
	public void testFindAllStudents(){
		List<Student> students = studentRepository.findAll();
		assert(students.size() == 3);
	}

	@Test
	public void testUpdateStudent() {
		Student student = studentRepository.findById(3L).orElseThrow();
		assert(!student.getName().equals("Mariusz"));
		student.setName("Mariusz");
		studentRepository.save(student);

		Student updatedStudent = studentRepository.findById(3L).orElseThrow();
		assert(updatedStudent.getName().equals("Mariusz"));
	}

	@Test
	public void testRemoveStudent() {
		Optional<Student> student = studentRepository.findById(3L);
		assert(student.isPresent());
		studentRepository.deleteById(3L);

		Optional<Student> studentAfterRemoval = studentRepository.findById(3L);
		assert(studentAfterRemoval.isEmpty());
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

	@Test
	public void testUpdateCourse() {
		Course course = courseRepository.findByName("Testowanie oprogramowania").get(0);
		assert(course.getEcts() == 4);
		course.setEcts(6);
		courseRepository.save(course);

		Course courseAfterUpdate = courseRepository.findByName("Testowanie oprogramowania").get(0);
		assert(courseAfterUpdate.getEcts() == 6);
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

	@Test
	public void testUpdateGrade() {
		Grade grade = gradeRepository.findBySemester("2010Z").get(0);
		assert(Math.abs(grade.getGrade() - 5.0F) < 0.0001);
		grade.setGrade(4.0F);
		gradeRepository.save(grade);

		Grade gradeAfterUpdate = gradeRepository.findBySemester("2010Z").get(0);
		assert(Math.abs(grade.getGrade() - 4.0F) < 0.0001);
	}

	@Test
	public void testRemoveGrade() {
		Grade grade = gradeRepository.findBySemester("2010Z").get(0);
		gradeRepository.delete(grade);

		List<Grade> gradeAfterRemoval = gradeRepository.findBySemester("2010Z");
		assert(gradeAfterRemoval.size() == 0);
	}

}
