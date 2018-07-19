package com.database.example.demo;

import com.database.example.demo.model.Class_;
import com.database.example.demo.model.Student;
import com.database.example.demo.model.Teacher;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;


public final class Container implements Serializable {
    @JsonProperty(value = "Students")
    public List<Student> students;
    @JsonProperty(value = "Teachers")
    public List<Teacher> teachers;
    @JsonProperty(value = "Classes")
    public List<Class_> class_s;


}
