package com.example.testowanieCRUD.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;


@Entity(name = "grades")
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotEmpty
    private String semester;

    @NotNull
    private float value;

    @ManyToOne
    @JoinColumn
    @JsonIgnore // prevents infinite loop when serializing
    private Student student;

    @ManyToOne
    @JoinColumn
    @JsonIgnore // prevents infinite loop when serializing
    private Course course;

    public Grade(@NotEmpty String semester, float value, @NotEmpty Student student, @NotEmpty Course course) {
        this.semester = semester;
        this.value = value;
        this.student = student;
        this.course = course;
    }

    public Grade() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public float getValue() {
        return value;
    }

    public Student getStudent() { return student; }

    public void setStudent(Student student) { this.student = student;}

    public Course getCourse() { return course; }

    public void setCourse(Course course) { this.course = course;}

    public void setValue(float value) {
        this.value = value;
    }
}
