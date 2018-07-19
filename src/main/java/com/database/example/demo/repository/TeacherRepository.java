package com.database.example.demo.repository;

import com.database.example.demo.model.Teacher;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TeacherRepository extends CrudRepository<Teacher, Integer> {

    List<Teacher> findAll();

    Optional<Teacher> findById(Integer id);

    List<Teacher> findByName(String name);

    List<Teacher> findByBranch(String branch);
}
