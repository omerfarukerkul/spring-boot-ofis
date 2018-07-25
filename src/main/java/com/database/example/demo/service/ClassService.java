package com.database.example.demo.service;

import com.database.example.demo.model.Class_;
import com.database.example.demo.repository.ClassRepository;
import com.database.example.demo.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public final class ClassService {
    private final ClassRepository classRepository;

    @Autowired
    public ClassService(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    public List<Class_> findAll() {
        return classRepository.findAll();
    }

    public Optional<Class_> findById(Integer id) {
        return classRepository.findById(id);
    }

    public boolean hasId(Integer id) {
        return classRepository.existsById(id);
    }

    public Class_ add(Integer a1,Integer a2){

        final Class_ c = new Class_();
        c.setClassName(Util.randomString(a1));
        c.setClassBranch(Util.randomString(a2));
        return classRepository.save(c);
    }

    public Class_ add() {
        final Class_ c = new Class_();
        c.setClassName(Util.randomString(10));
        c.setClassBranch(Util.randomString(10));
        return classRepository.save(c);
    }

    public boolean delete() {
        final List<Class_> c = classRepository.findAll();
        if (!c.isEmpty()) {
            Class_ removeClass = c.get(c.size() - 1);
            if (removeClass == null)
                return false;
            classRepository.deleteById(removeClass.getId());
            return true;
        }
        return false;
    }
}
