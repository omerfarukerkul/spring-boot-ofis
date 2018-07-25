package com.database.example.demo.service;

import com.database.example.demo.model.Branch;
import com.database.example.demo.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchService {
    private final BranchRepository branchRepository;

    @Autowired
    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    public List<Branch> findAll() {
        return branchRepository.findAll();
    }

    public Branch findById(Integer id) {
        return branchRepository
                .findById(id)
                .orElse(null);
    }

    public Branch findByName(String name) {
        return branchRepository
                .findByBranchName(name)
                .orElse(null);
    }

    public Branch add(String branchName) {
        if (branchRepository.findByBranchName(branchName).isPresent()) {
            return null;
        } else {
            final Branch b = new Branch();
            b.setBranchName(branchName);
            return branchRepository.save(b);
        }
    }
}
