package com.example.testowanieCRUD.view;

import com.example.testowanieCRUD.entity.Course;
import com.example.testowanieCRUD.repository.CourseRepository;
import com.example.testowanieCRUD.service.CourseService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value="gui/courses", layout = MainLayout.class)
public class CoursesView extends VerticalLayout {
    private final CourseRepository courseRepository;
    private final CourseService courseService;
    final Grid<Course> grid;
    private final Button allBtn;
    private final Text ectsSum;
    private final Button addBtn;
    private final CourseEditor courseEditor;

    public CoursesView(CourseRepository repository, CourseService service, CourseEditor editor) {
        this.courseRepository = repository;
        this.courseService = service;
        this.grid = new Grid<>(Course.class);
        this.allBtn = new Button("All courses");
        this.ectsSum = new Text("ECTS sum of all courses: " + courseService.getECTSSum());
        this.addBtn = new Button("Add course");
        this.courseEditor = editor;

        grid.setColumns("id", "name", "ects");
        grid.getColumnByKey("id").setWidth("50px").setFlexGrow(0);
        grid.setItems(courseRepository.findAll());
        add(grid, allBtn, ectsSum, addBtn, editor);


        grid.asSingleSelect().addValueChangeListener(e -> {
            editor.editCourse(e.getValue());
        });

        allBtn.addClickListener(e -> grid.setItems(courseRepository.findAll()));
        addBtn.addClickListener(e -> editor.editCourse(new Course()));

        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            grid.setItems(courseRepository.findAll());
            ectsSum.setText("ECTS sum of all courses: " + courseService.getECTSSum());
        });
    }

}
