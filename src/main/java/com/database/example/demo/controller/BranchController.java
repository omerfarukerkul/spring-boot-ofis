package com.database.example.demo.controller;

import com.database.example.demo.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BranchController {

    private final BranchService branchService;

    @Autowired
    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    // TODO: 25-Jul-18 Branch anasayfası
    @GetMapping(value = "/branch", produces = {MediaType.TEXT_HTML_VALUE})
    public String branchPage() {
        return "branch";
    }
}
