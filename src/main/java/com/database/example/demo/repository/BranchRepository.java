package com.database.example.demo.repository;

import com.database.example.demo.model.Branch;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BranchRepository extends CrudRepository<Branch, Integer> {

    List<Branch> findAll();

    Optional<Branch> findById(Integer id);

    Optional<Branch> findByBranchName(String name);

}
