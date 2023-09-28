package com.PRG371.Java_SpringBoot_WebApp.login;

import com.PRG371.Java_SpringBoot_WebApp.student.Student;
import com.PRG371.Java_SpringBoot_WebApp.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.PRG371.Java_SpringBoot_WebApp.admin.AdministratorRepository;
import com.PRG371.Java_SpringBoot_WebApp.admin.Administrator;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AdministratorRepository administratorRepository;

    @GetMapping
    public String showLoginPage() {
        return "login"; // Return login.html template
    }

    @PostMapping("/")
    public String logout() {
        // Perform logout logic here (if needed)
        // For example, you might clear session attributes or authentication status.

        return "redirect:/";
    }

//    @PostMapping("/login")
//    public String login(@RequestParam String userType, @RequestParam String userIdentifier, @RequestParam String userPassword, Model model) {
//        try {
//            boolean credentialsValid = false;
//
//            if ("student".equals(userType)) {
//                Optional<Student> student = studentRepository.findByEmailAndPassword(userIdentifier, userPassword);
//                if (student.isPresent()) {
//                    credentialsValid = true;
//                }
//            } else if ("admin".equals(userType)) {
//                Optional<Administrator> admin = administratorRepository.findByAdminNameAndPassword(userIdentifier, userPassword); // Update method name
//                if (admin.isPresent()) {
//                    credentialsValid = true;
//                }
//            }
//
//            if (credentialsValid) {
//                if ("student".equals(userType)) {
//                    return showStudentPage(); // Redirect to student page
//                } else if ("admin".equals(userType)) {
//                    return "admin"; // Return the name of the admin HTML template
//                }
//            }
//
//            // If no valid credentials, show the login page with a message
//            model.addAttribute("invalidCredentials", true);
//            return "login"; // Return to login page
//        } catch (Exception e) {
//            // If no valid credentials, show the login page with a message
//            model.addAttribute("invalidCredentials", true);
//            return "login"; // Return to login page;
//        }
//
//
//    }

    @PostMapping("/login")
    public String login(@RequestParam String userType, @RequestParam String userIdentifier, @RequestParam String userPassword, Model model) {
        try {
            boolean credentialsValid = false;

            if ("student".equals(userType)) {
                Optional<Student> student = studentRepository.findByEmailAndPassword(userIdentifier, userPassword);
                if (student.isPresent()) {
                    credentialsValid = true;
                }
            } else if ("admin".equals(userType)) {
                Optional<Administrator> admin = administratorRepository.findByAdminNameAndPassword(userIdentifier, userPassword);
                if (admin.isPresent()) {
                    credentialsValid = true;
                }
            }

            if (credentialsValid) {
                if ("student".equals(userType)) {
                    return showStudentPage(); // Redirect to student page
                } else if ("admin".equals(userType)) {
                    // Redirect to admin page
                    return "redirect:/admin"; // Use the appropriate URL mapping for your admin page
                }
            }

            // If no valid credentials, show the login page with a message
            model.addAttribute("invalidCredentials", true);
            return "login"; // Return to login page
        } catch (Exception e) {
            // If an exception occurs, show the login page with a message
            model.addAttribute("invalidCredentials", true);
            return "login"; // Return to login page;
        }
    }

    @GetMapping("/student_page")
public String showStudentPage() {
    return "student_page"; // Return the name of the student_page HTML template
}
}


