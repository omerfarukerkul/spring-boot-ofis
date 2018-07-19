package com.database.example.demo.service;

import com.database.example.demo.model.Class_;
import com.database.example.demo.model.Student;
import com.database.example.demo.model.Teacher;
import com.database.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public final class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public List<Student> findByName(String name) {
        return studentRepository.findByName(name);
    }

    public Optional<Student> findById(Integer id) {
        return studentRepository.findById(id);
    }

    public void deleteByName(String name) {
        studentRepository.deleteByName(name);
    }

    public boolean hasId(Integer id) {
        return studentRepository.existsById(id);
    }

    public Student add(String name, Class_ class_, Teacher teacher) {

        final Student s = new Student();
        s.setName(name);
        s.setClass_(class_);
        s.setTeacher(teacher);
        return studentRepository.save(s);
    }

}
