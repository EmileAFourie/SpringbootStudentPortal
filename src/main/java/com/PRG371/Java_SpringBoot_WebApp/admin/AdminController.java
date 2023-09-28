package com.PRG371.Java_SpringBoot_WebApp.admin;

import com.PRG371.Java_SpringBoot_WebApp.student.ResourceNotFoundException;
import com.PRG371.Java_SpringBoot_WebApp.student.Student;
import com.PRG371.Java_SpringBoot_WebApp.student.StudentRepository;
import com.PRG371.Java_SpringBoot_WebApp.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    private final StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    public AdminController(StudentService studentService) {
        this.studentService = studentService;
    }


//    public class FooController{
//        @ExceptionHandler({ CustomExceptional.class, CustomException2.class})
//        public void handleException(){
//
//        }
//
//    }



    @GetMapping
    public String adminPage(Model model) {
        model.addAttribute("students", studentService.getStudents());
        System.out.println("Number of students retrieved: " + studentService.getStudents().size()); // Add this line
        return "admin";
    }

    @PostMapping("/edit/{id}")
    public String updateStudentForm(@PathVariable int id, @ModelAttribute Student student) {
        studentService.updateStudent(id, student.getStudentName(), student.getStudentEmail(), student.getStudentAddress(), student.getStudentPassword(), student.getStudentCourse());
        return "redirect:/admin";
    }


    @PostMapping("/update/{studentId}")
    public ResponseEntity<String> updateStudent(
            @PathVariable int studentId,
            @RequestBody Student updatedStudent
    ) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));

        student.setStudentName(updatedStudent.getStudentName());
        student.setStudentEmail(updatedStudent.getStudentEmail());
        student.setStudentAddress(updatedStudent.getStudentAddress());
        studentRepository.save(student);

        return ResponseEntity.ok("Student updated successfully");
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id) {
        try {
            studentService.deleteStudent(id);
            return ResponseEntity.ok("Student deleted successfully");
        } catch (Exception e) {
            logger.error("An error occurred while deleting student with ID: " + id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while deleting student.");
        }
    }



    @PostMapping("/add")
    public String addStudent(@ModelAttribute Student student) {
        studentService.addNewStudent(student);
        return "redirect:/admin";
    }

    @PostMapping("/admin/add")
    public String addStudent(@RequestParam String studentName,
                             @RequestParam String studentEmail,
                             @RequestParam String studentAddress) {
        Student newStudent = new Student(studentName, studentAddress, studentEmail, null, ""); // Assuming you set the password later
        studentService.addNewStudent(newStudent);
        return "redirect:/admin";
    }
}