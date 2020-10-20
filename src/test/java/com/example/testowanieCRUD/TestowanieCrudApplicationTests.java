package com.example.testowanieCRUD;

import com.example.testowanieCRUD.entity.Student;
import com.example.testowanieCRUD.repository.StudentRepository;
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
		Student student = studentRepository.save(new Student("Ala", LocalDate.of(1999, 4, 4)));
		assert(student.getId() > 0);
	}

	@Test
	public void testFindStudentByName(){
		Student student = studentRepository.findByNameIgnoreCase("Damian").get(0);
		assert(student.getName().compareTo("Damian") == 0);
	}
}
