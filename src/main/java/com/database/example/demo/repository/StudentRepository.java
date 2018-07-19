package com.database.example.demo.repository;

import com.database.example.demo.model.Student;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface StudentRepository extends CrudRepository<Student, Integer> {
    List<Student> findAll();

    Optional<Student> findById(Integer id);

    List<Student> findByName(String s);

    @Modifying
    @Transactional
    void deleteByName(String name);

}
