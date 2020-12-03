package com.example.testowanieCRUD.view;


import com.example.testowanieCRUD.entity.Course;
import com.example.testowanieCRUD.entity.Grade;
import com.example.testowanieCRUD.entity.Student;
import com.example.testowanieCRUD.repository.CourseRepository;
import com.example.testowanieCRUD.repository.GradeRepository;
import com.example.testowanieCRUD.repository.StudentRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.PropertyId;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class GradeEditor extends VerticalLayout {

    private final GradeRepository repository;
    private final CourseRepository crepo;
    private final StudentRepository srepo;
    private Grade grade;

    TextField semester = new TextField("Semester");
    NumberField value = new NumberField("Value");
    ComboBox<Course> course_id = new ComboBox<>("Course");
    ComboBox<Student> student_id = new ComboBox<>("Student");

    Button save = new Button("Save");
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete");
    HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);

    Binder<Grade> binder = new Binder<>(Grade.class);
    private ChangeHandler changeHandler;

    @Autowired
    public GradeEditor(GradeRepository repository, CourseRepository crepo, StudentRepository srepo) {
        this.repository = repository;
        this.crepo = crepo;
        this.srepo = srepo;

        add(semester, value, course_id, student_id, actions);
        binder.forField(semester).bind(Grade::getSemester, Grade::setSemester);
        binder.forField(value).withConverter(Double::floatValue, Double::valueOf).bind(Grade::getValue, Grade::setValue);
        binder.forField(course_id).bind(Grade::getCourse, Grade::setCourse);
        binder.forField(student_id).bind(Grade::getStudent, Grade::setStudent);

        student_id.setItems(srepo.findAll());
        student_id.setItemLabelGenerator(Student::getName);

        setSpacing(true);

        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> editGrade(grade));
        setVisible(false);
    }

    void delete() {
        repository.delete(grade);
        changeHandler.onChange();
    }

    void save() {
        repository.save(grade);
        changeHandler.onChange();
    }

    public interface ChangeHandler {
        void onChange();
    }

    public final void editGrade(Grade s) {
        if (s == null) {
            setVisible(false);
            return;
        }
        course_id.setItems(s.getStudent().getCourses());
        course_id.setItemLabelGenerator(Course::getName);
        final boolean persisted = s.getId() != null;
        if (persisted) {
            grade = repository.findById(s.getId()).get();
        }
        else {
            grade = s;
        }
        cancel.setVisible(persisted);

        binder.setBean(grade);


        setVisible(true);
    }

    public void setChangeHandler(ChangeHandler h) {
        changeHandler = h;
    }

}