package com.example.testowanieCRUD;

import com.example.testowanieCRUD.entity.Student;
import com.example.testowanieCRUD.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class StudentRepositoryTests {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void testCreateStudent() {
        Student student = studentRepository.save(new Student("Ala", LocalDate.of(2000, 4, 4)));
        assert (student.getId() > 0);
    }

    @Test
    public void testFindStudentByName() {
        Student student = studentRepository.findByNameIgnoreCase("Damian").get(0);
        assert (student.getName().compareTo("Damian") == 0);
    }

    @Test
    public void testFindStudentByDateOfBirth() {
        Student student = studentRepository.findByDateOfBirth(LocalDate.of(1990, 3, 3)).get(0);
        assert (student.getName().compareTo("Szymon") == 0);
    }

    @Test
    public void testFindStudentById() {
        Optional<Student> student = studentRepository.findById(3L);
        assert (student.isPresent() && (student.get().getId() == 3L));
    }

    @Test
    public void testFindAllStudents() {
        List<Student> students = studentRepository.findAll();
        assert (students.size() == 3);
    }

    @Test
    public void testUpdateStudent() {
        Student student = studentRepository.findById(3L).orElseThrow();
        assert (!student.getName().equals("Mariusz"));
        student.setName("Mariusz");
        studentRepository.save(student);

        Student updatedStudent = studentRepository.findById(3L).orElseThrow();
        assert (updatedStudent.getName().equals("Mariusz"));
    }

    @Test
    public void testRemoveStudent() {
        Optional<Student> student = studentRepository.findById(3L);
        assert (student.isPresent());
        studentRepository.deleteById(3L);

        Optional<Student> studentAfterRemoval = studentRepository.findById(3L);
        assert (studentAfterRemoval.isEmpty());
    }
}
