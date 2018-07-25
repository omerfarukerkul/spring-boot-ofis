package com.database.example.demo.repository;

import com.database.example.demo.model.Class_;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface ClassRepository extends CrudRepository<Class_,Integer> {

    List<Class_> findAll();

    Optional<Class_> findById(Integer id);

    @Modifying
    @Transactional
    void deleteById(Integer id);
}
