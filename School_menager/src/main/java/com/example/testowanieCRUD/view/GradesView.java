package com.example.testowanieCRUD.view;

import com.example.testowanieCRUD.entity.Grade;
import com.example.testowanieCRUD.entity.Student;
import com.example.testowanieCRUD.repository.GradeRepository;
import com.example.testowanieCRUD.service.GradeService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value="gui/grades", layout = MainLayout.class)
public class GradesView extends VerticalLayout {
    private final GradeRepository gradeRepository;
    private final GradeService gradeService;
    final Grid<Grade> grid;
    private final Button allBtn;
    private final Button failedBtn;
    private final Button passedBtn;
    private final Button addBtn;
    private final GradeEditor editor;

    public GradesView(GradeRepository repository, GradeService service, GradeEditor editor) {
        this.gradeRepository = repository;
        this.gradeService = service;
        this.grid = new Grid<>(Grade.class);
        this.allBtn = new Button("All grades");
        this.failedBtn = new Button("Failing grades");
        this.passedBtn = new Button("Passing grades");
        this.addBtn = new Button("Add grade");
        this.editor = editor;

        HorizontalLayout buttons = new HorizontalLayout(allBtn, failedBtn, passedBtn);
        add(grid, buttons, addBtn, editor);

        grid.setColumns("id", "semester", "value");
        grid.getColumnByKey("id").setWidth("50px").setFlexGrow(0);
        grid.addColumn(grade -> grade.getCourse().getName()).setHeader("Course");
        grid.addColumn(grade -> grade.getStudent().getName()).setHeader("Student");
        grid.setItems(gradeRepository.findAll());

        grid.asSingleSelect().addValueChangeListener(e -> {
            editor.editGrade(e.getValue());
        });

        allBtn.addClickListener(e -> grid.setItems(gradeRepository.findAll()));
        failedBtn.addClickListener(e -> grid.setItems(gradeService.getFailedGrades()));
        passedBtn.addClickListener(e -> grid.setItems(gradeService.getPassingGrades()));
        addBtn.addClickListener(e -> editor.editGrade(new Grade()));

        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            grid.setItems(gradeRepository.findAll());
        });
    }
}
