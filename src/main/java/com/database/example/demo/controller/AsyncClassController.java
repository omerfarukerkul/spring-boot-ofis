package com.database.example.demo.controller;

import com.database.example.demo.model.Class_;
import com.database.example.demo.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AsyncClassController {
    private final ClassService classService;


    @Autowired
    public AsyncClassController(ClassService classService) {
        this.classService = classService;
    }

    @ResponseBody
    @PostMapping(value = "/removeBranchClass", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}/*, consumes = {MediaType.ALL_VALUE}*/)
    public ResponseEntity<Boolean> removeBranch() {
        return ResponseEntity.ok(classService.delete());
    }

    @ResponseBody
    @PostMapping(value = "/addBranchClass", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<Class_> addBranch() {
        return ResponseEntity.ok(classService.add());
    }
}
