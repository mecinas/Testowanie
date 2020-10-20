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
    private float grade;

    @ManyToOne
    @JoinColumn
    @JsonIgnore // prevents infinite loop when serializing
    private Student student;

    @ManyToOne
    @JoinColumn
    @JsonIgnore // prevents infinite loop when serializing
    private Course course;

    public Grade(@NotEmpty String semester, float grade, @NotEmpty Student student, @NotEmpty Course course) {
        this.semester = semester;
        this.grade = grade;
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

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }
}
