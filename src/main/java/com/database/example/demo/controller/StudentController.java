package com.database.example.demo.controller;

import com.database.example.demo.model.Class_;
import com.database.example.demo.model.Student;
import com.database.example.demo.model.Teacher;
import com.database.example.demo.repository.ClassRepository;
import com.database.example.demo.repository.StudentRepository;
import com.database.example.demo.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class StudentController {
    private final ClassRepository classRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    static final List<Character> randomChars = new ArrayList<>();

    static {
        for (char c = 'a'; c <= 'z'; c++) {
            randomChars.add(c);
        }
        for (char c = 'A'; c <= 'Z'; c++) {
            randomChars.add(c);
        }
    }

    @Autowired
    public StudentController(ClassRepository classRepository
            , StudentRepository studentRepository
            , TeacherRepository teacherRepository) {
        this.classRepository = classRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    @GetMapping(value = "/", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<Student> showStudents() {
        return studentRepository.findAll();
    }

    @GetMapping(value = "/deneme/{path:^[\\d]*$}",produces = {MediaType.TEXT_HTML_VALUE})
    public String denemeRegex(@PathVariable(name = "path" , required = false) String path,
                              ModelMap model){
        model.addAttribute("path",path);

        return "test";
    }

    @GetMapping(value = "/test", produces = {MediaType.TEXT_HTML_VALUE})
    public String addStudentOnEveryRequest(@RequestParam(name = "n",required = false) String name,
                                           @RequestParam(name = "t",required = false) String tName,
                                           ModelMap model
    ) {
//        Student s = new Student();
//        Class_ c = new Class_();
//        Teacher t = new Teacher();
//
//        c.setClassName(randomString(15));
//        c.setClassBranch(randomString(5));
//        c = classRepository.save(c);
//
//        t.setName(tName);
//        t.setBranch("Computer Engineer");
//        t.setClass_(c);
//        t = teacherRepository.save(t);
//
//        s.setName(name);
//        s.setClass_(c);
//        s.setTeacher(t);
//        s = studentRepository.save(s);
//
//        System.out.println(name + " --> " + s.getClass_().getClassName());
//
//        model.addAttribute("class", c);
//        model.addAttribute("student", s);
//        model.addAttribute("teacher", t);
        return "index";
    }


    @PostMapping(value = "/", produces = {MediaType.TEXT_HTML_VALUE})
    public String getAllId(@RequestParam(name = "student", required = false) String sname,
                           @RequestParam(name = "teacher", required = false) String tname,
                           RedirectAttributes redir) {

        List<Student> s = studentRepository.findByName(sname);
        List<Teacher> t = teacherRepository.findByName(tname);

        redir.addFlashAttribute("teacherId",t.isEmpty() ? -1 : t.get(0).getId());
        redir.addFlashAttribute("studentId",s.isEmpty() ? -1 : s.get(0).getId());
        return "redirect:/test";
    }


    private static String randomString(int length) {
        Random random = new Random();
        String randStr = "";
        for (int i = 0; i < length; i++) {
            randStr += randomChars.get(random.nextInt(randomChars.size()));
        }
        return randStr;
    }
}
