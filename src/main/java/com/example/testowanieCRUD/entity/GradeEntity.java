package com.example.testowanieCRUD.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;


@Entity(name = "grades")
public class GradeEntity {

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
    private StudentEntity student;

    @ManyToOne
    @JoinColumn
    @JsonIgnore // prevents infinite loop when serializing
    private CourseEntity course;

    public GradeEntity(@NotEmpty String semester, float grade, StudentEntity student, CourseEntity course) {
        this.semester = semester;
        this.grade = grade;
        this.student = student;
        this.course = course;
    }

    public GradeEntity() {

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
