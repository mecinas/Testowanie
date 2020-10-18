package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Student {
    private final String name;
    private final UUID id;

    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }

    public Student(@JsonProperty("id") UUID id,
                   @JsonProperty("name") String name) {
        this.name = name;
        this.id = id;
    }
}
