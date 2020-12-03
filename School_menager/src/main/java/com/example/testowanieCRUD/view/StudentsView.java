package com.example.testowanieCRUD.view;

import com.example.testowanieCRUD.entity.Student;
import com.example.testowanieCRUD.repository.StudentRepository;
import com.example.testowanieCRUD.service.StudentService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value="gui/students", layout = MainLayout.class)
public class StudentsView extends VerticalLayout {
    private final StudentRepository studentRepository;
    private final StudentService studentService;
    final Grid<Student> grid;
    private final Button allBtn;
    private final Button addBtn;
    private final StudentEditor studentEditor;

    public StudentsView(StudentRepository repository, StudentService service, StudentEditor editor) {
        this.studentRepository = repository;
        this.studentService = service;
        this.grid = new Grid<>(Student.class);
        this.allBtn = new Button("All students");
        this.addBtn = new Button("Add student");
        this.studentEditor = editor;

        grid.setColumns("id", "name", "dateOfBirth");
        grid.getColumnByKey("id").setWidth("50px").setFlexGrow(0);
        grid.addColumn(student -> studentService.getAverageGrade(student.getId())).setHeader("Average grade");
        grid.addColumn(student -> studentService.getStudentsECTS(student.getId())).setHeader("ECTS");
        grid.addColumn(student -> studentService.checkStudentsECTS(studentService.getStudentsECTS(student.getId()))).setHeader("ECTS status");
        grid.setItems(studentRepository.findAll());
        add(grid, allBtn, addBtn, editor);


        grid.asSingleSelect().addValueChangeListener(e -> {
            editor.editStudent(e.getValue());
        });

        allBtn.addClickListener(e -> grid.setItems(studentRepository.findAll()));
        addBtn.addClickListener(e -> editor.editStudent(new Student()));

        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            grid.setItems(studentRepository.findAll());
        });
    }

}
