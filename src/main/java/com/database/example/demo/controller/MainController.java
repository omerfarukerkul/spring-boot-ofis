package com.database.example.demo.controller;

import com.database.example.demo.model.Class_;
import com.database.example.demo.model.Student;
import com.database.example.demo.model.Teacher;
import com.database.example.demo.service.ClassService;
import com.database.example.demo.service.StudentService;
import com.database.example.demo.service.TeacherService;
import com.database.example.demo.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public final class MainController {
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final ClassService classService;

    @Autowired
    public MainController(StudentService studentService
            , TeacherService teacherService
            , ClassService classService) {
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.classService = classService;
    }

    @ResponseBody
    @GetMapping(value = "/", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<Student> showStudents() {
        return studentService.findAll();
    }

    @GetMapping(value = "/deneme/{path:^[\\d]*$}", produces = {MediaType.TEXT_HTML_VALUE})
    public String denemeRegex(@PathVariable(name = "path", required = false) String path,
                              ModelMap model) {
        model.addAttribute("path", path);

        return "/";
    }

    @GetMapping(value = "/test", produces = {MediaType.TEXT_HTML_VALUE})
    public String test() {
        return "index";
    }


    @PostMapping(value = "/test", produces = {MediaType.TEXT_HTML_VALUE})
    public String addSchool(@RequestParam(name = "student", required = false, defaultValue = "primitiveName") String name,
                            @RequestParam(name = "teacher", required = false, defaultValue = "defaultTeacherName") String tName,
                            @RequestParam(name = "branch", required = false, defaultValue = "defaultBranch") String branch,
                            RedirectAttributes redir
    ) {
        final Class_ c = classService.add(15, 5);
        final Teacher t = teacherService.add(tName, branch, c);
        final Student s = studentService.add(name, c, t);

        System.out.println(s.getName() + " --> " + t.getName() + " <-- " + t.getBranch());

        redir.addFlashAttribute("class_",c);
        redir.addFlashAttribute("student",s);
        redir.addFlashAttribute("branch",t);

        return "redirect:/";
    }


    @PostMapping(value = "/", produces = {MediaType.TEXT_HTML_VALUE})
    public String getAllId(@RequestParam(name = "student", required = false) String sname,
                           @RequestParam(name = "teacher", required = false) String tname,
                           RedirectAttributes redir) {

        List<Student> s = studentService.findByName(sname);
        List<Teacher> t = teacherService.findByName(tname);

        redir.addFlashAttribute("teacherId", t.isEmpty() ? -1 : t.get(0).getId());
        redir.addFlashAttribute("studentId", s.isEmpty() ? -1 : s.get(0).getId());
        return "redirect:/";
    }


}
