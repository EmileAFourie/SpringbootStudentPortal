package com.PRG371.Java_SpringBoot_WebApp.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(path="registration")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public void RegisterStudent(@RequestBody Student student)
    {
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path="{studentId}")
    public void DeleteStudent(@PathVariable("studentId") int studentId)
    {
        studentService.deleteStudent(studentId);
    }

    @PutMapping("/{id}")
    public String updateStudent(@PathVariable int id, @RequestBody Student student) {
        studentService.updateStudent(id, student.getStudentName(), student.getStudentEmail(), student.getStudentAddress(), student.getStudentPassword(), student.getStudentCourse());
        return "redirect:/admin";
    }

    @GetMapping
    public String registration(Model model) {
        Student student = new Student();
        model.addAttribute("student",student);
        return "registration";
    }
    @PostMapping("/registerStudent")
    public String registerStudent(@ModelAttribute("student") Student student) {
        System.out.println(student);
        studentService.addNewStudent(student);
        return "login";
    }
}
