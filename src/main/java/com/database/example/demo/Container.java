package com.database.example.demo;

import com.database.example.demo.model.Branch;
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
    @JsonProperty(value = "Branches")
    public List<Branch> branches;

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public List<Class_> getClass_s() {
        return class_s;
    }

    public void setClass_s(List<Class_> class_s) {
        this.class_s = class_s;
    }

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }

}
