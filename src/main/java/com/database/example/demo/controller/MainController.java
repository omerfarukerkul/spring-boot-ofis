package com.database.example.demo.controller;

import com.database.example.demo.Container;
import com.database.example.demo.model.Branch;
import com.database.example.demo.model.Class_;
import com.database.example.demo.model.Student;
import com.database.example.demo.model.Teacher;
import com.database.example.demo.service.BranchService;
import com.database.example.demo.service.ClassService;
import com.database.example.demo.service.StudentService;
import com.database.example.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public final class MainController {
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final ClassService classService;
    private final BranchService branchService;

    @Autowired
    public MainController(StudentService studentService
            , TeacherService teacherService
            , ClassService classService
            , BranchService branchService) {
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.classService = classService;
        this.branchService = branchService;
    }

    @GetMapping(value = "/class", produces = {MediaType.TEXT_HTML_VALUE})
    public String showStudents(ModelMap model) {

        Container c = new Container();
        c.students = studentService.findAll();
        c.class_s = classService.findAll();
        c.teachers = teacherService.findAll();
        c.branches = branchService.findAll();

        model.addAttribute("container", c);
        return "class";
    }


    @GetMapping(value = "/deneme/{path:^[\\d]*$}", produces = {MediaType.TEXT_HTML_VALUE})
    public String denemeRegex(@PathVariable(name = "path", required = false) String path,
                              RedirectAttributes redir) {
        redir.addFlashAttribute("path", path);

        return "redirect:/";
    }

    /*@GetMapping(value = "/test", produces = {MediaType.TEXT_HTML_VALUE})
    public String test(ModelMap modelMap) {

        modelMap.addAttribute("branches", branchService
                .findAll()
                .stream()
                .sorted()
                .collect(Collectors.toList()));

        modelMap.addAttribute("teachers", teacherService
                .findAll()
                .stream()
                .sorted(Comparator.comparing(Teacher::getId))
                .collect(Collectors.toList()));

        return "index";
    }*/


    @PostMapping(value = "/test", produces = {MediaType.TEXT_HTML_VALUE})
    public String addSchool(@RequestParam(name = "teacher", required = false, defaultValue = "defaultTeacherName") String tName,
                            @RequestParam(name = "branch", required = false) String branch,
                            RedirectAttributes redir
    ) {
        final Class_ c = classService.add(15, 5);
        final Branch b = branchService.add(branch);
        final Teacher t = teacherService.add(tName, b, c);

        redir.addFlashAttribute("teacherBranch", t.getBranchId().getBranchName());
        redir.addFlashAttribute("teacherId", t.getId());
        redir.addFlashAttribute("teacherClass", t.getClassId().getClassName());
        redir.addFlashAttribute("teacherName", t.getName());

        return "redirect:/";
    }

    @GetMapping(value = "/", produces = {MediaType.TEXT_HTML_VALUE})
    public String index() {
        return "index";
    }
    @PostMapping(value = "/", produces = {MediaType.TEXT_HTML_VALUE})
    public String getAllId(@RequestParam(name = "student", required = false) String sname,
                           @RequestParam(name = "teacher", required = false) String tname,
                           RedirectAttributes redir) {

        List<Student> s = studentService.findByName(sname);
        List<Teacher> t = teacherService.findByName(tname);

        redir.addFlashAttribute("teacher", t.isEmpty() ? -1 : t.get(0));
        redir.addFlashAttribute("student", s.isEmpty() ? -1 : s.get(0));
        return "redirect:/";
    }

}
