package com.database.example.demo.service;

import com.database.example.demo.model.Branch;
import com.database.example.demo.model.Class_;
import com.database.example.demo.model.Teacher;
import com.database.example.demo.repository.BranchRepository;
import com.database.example.demo.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class TeacherService {
    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository, BranchRepository branchRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    public List<Teacher> findByName(String name) {
        return teacherRepository.findByName(name);
    }

    public Teacher findById(Integer id) {
        return teacherRepository
                .findById(id)
                .orElse(null);
    }

    public boolean hasId(Integer id) {
        return teacherRepository.existsById(id);
    }

    public Teacher add(String name, Branch branch, Class_ c) {

        final Teacher t = new Teacher();
        t.setName(name);
        t.setBranchId(branch);
        t.setClassId(c);
        return teacherRepository.save(t);
    }

}
