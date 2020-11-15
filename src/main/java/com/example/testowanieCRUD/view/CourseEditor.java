package com.example.testowanieCRUD.view;


import com.example.testowanieCRUD.entity.Course;
import com.example.testowanieCRUD.repository.CourseRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class CourseEditor extends VerticalLayout {

    private final CourseRepository repository;
    private Course course;

    TextField name = new TextField("Name");
    IntegerField ects = new IntegerField("ECTS");

    Button save = new Button("Save");
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete");
    HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);

    Binder<Course> binder = new Binder<>(Course.class);
    private ChangeHandler changeHandler;

    @Autowired
    public CourseEditor(CourseRepository repository) {
        this.repository = repository;

        add(name, ects, actions);

        binder.bindInstanceFields(this);

        setSpacing(true);

        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> editCourse(course));
        setVisible(false);
    }

    void delete() {
        repository.delete(course);
        changeHandler.onChange();
    }

    void save() {
        repository.save(course);
        changeHandler.onChange();
    }

    public interface ChangeHandler {
        void onChange();
    }

    public final void editCourse(Course s) {
        if (s == null) {
            setVisible(false);
            return;
        }
        final boolean persisted = s.getId() != null;
        if (persisted) {
            course = repository.findById(s.getId()).get();
        }
        else {
            course = s;
        }
        cancel.setVisible(persisted);

        binder.setBean(course);

        setVisible(true);
    }

    public void setChangeHandler(ChangeHandler h) {
        changeHandler = h;
    }

}