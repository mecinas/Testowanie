package com.example.testowanieCRUD.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;


@Entity(name = "courses")
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    private int ects;

    @ManyToMany(mappedBy = "courses")
    @JsonIgnore // prevents infinite loop when serializing
    private Set<StudentEntity> students = new HashSet<>();

    @OneToMany(mappedBy = "course")
    private Set<GradeEntity> grades = new HashSet<>();

    public Set<StudentEntity> getStudents() {
        return students;
    }

    public CourseEntity(String name, int ects) {
        this.name = name;
        this.ects = ects;
    }

    public CourseEntity() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEcts() {
        return ects;
    }

    public void setEcts(int ects) {
        this.ects = ects;
    }
}
