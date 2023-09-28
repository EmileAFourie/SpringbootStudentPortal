package com.PRG371.Java_SpringBoot_WebApp.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepo;

    @Autowired
    public StudentService(StudentRepository studentRepo) {
        this.studentRepo = studentRepo;
    }

    public List<Student> getStudents() {
        return studentRepo.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepo.findStudentByEmail(student.getStudentEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("Email Taken.");
        }
        studentRepo.save(student);
    }

    public void deleteStudent(int studentId) {
        boolean exists = studentRepo.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException("Student with ID: " + studentId + " does not exist.");
        }

        studentRepo.deleteById(studentId);
    }

    public void updateStudent(int studentId, String name, String email, String address, String password, String course) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("Student with ID: " + studentId + " does not exist."));

        if (name != null && !name.isEmpty() && !Objects.equals(student.getStudentName(), name)) {
            student.setStudentName(name);
        }

        if (email != null && !email.isEmpty() && !Objects.equals(student.getStudentEmail(), email)) {
            Optional<Student> studentOptional = studentRepo.findStudentByEmail(email);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("Email Taken.");
            }

            student.setStudentEmail(email);
        }

        if (address != null && !address.isEmpty() && !Objects.equals(student.getStudentAddress(), address)) {
            student.setStudentAddress(address);
        }

        if (password != null && !password.isEmpty() && !Objects.equals(student.getStudentPassword(), password)) {
            student.setStudentPassword(password);
        }

        if (course != null && !course.isEmpty() && !Objects.equals(student.getStudentCourse(), course)) {
            student.setStudentCourse(course);
        }
    }

    public Student getStudentById(int id) {
        return studentRepo.findById(id).orElse(null);
    }
}