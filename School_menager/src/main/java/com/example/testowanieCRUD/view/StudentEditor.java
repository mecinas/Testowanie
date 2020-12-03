package com.example.testowanieCRUD.view;

import com.example.testowanieCRUD.entity.Student;
import com.example.testowanieCRUD.repository.StudentRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;


@SpringComponent
@UIScope
public class StudentEditor extends VerticalLayout {

    private final StudentRepository repository;
    private Student student;

    TextField name = new TextField("Name");
    DatePicker dateOfBirth = new DatePicker("Date of birth");

    Button save = new Button("Save");
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete");
    HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);

    Binder<Student> binder = new Binder<>(Student.class);
    private ChangeHandler changeHandler;

    @Autowired
    public StudentEditor(StudentRepository repository) {
        this.repository = repository;

        add(name, dateOfBirth, actions);

        binder.bindInstanceFields(this);

        setSpacing(true);

        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> editStudent(student));
        setVisible(false);
    }

    void delete() {
        repository.delete(student);
        changeHandler.onChange();
    }

    void save() {
        repository.save(student);
        changeHandler.onChange();
    }

    public interface ChangeHandler {
        void onChange();
    }

    public final void editStudent(Student s) {
        if (s == null) {
            setVisible(false);
            return;
        }
        final boolean persisted = s.getId() != null;
        if (persisted) {
            student = repository.findById(s.getId()).get();
        }
        else {
            student = s;
        }
        cancel.setVisible(persisted);

        binder.setBean(student);

        setVisible(true);
    }

    public void setChangeHandler(ChangeHandler h) {
        changeHandler = h;
    }

}