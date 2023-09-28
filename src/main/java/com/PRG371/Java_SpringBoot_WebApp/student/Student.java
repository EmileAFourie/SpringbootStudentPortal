package com.PRG371.Java_SpringBoot_WebApp.student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Data
@AllArgsConstructor
@Entity
@Table(name = "student")
public class Student {
    // Getter and Setter methods
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")
    @Column(name = "student_id")
    private int studentId;

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "student_address")
    private String studentAddress;

    @Column(name = "student_email", unique = true)
    private String studentEmail;

    @Column(name = "student_password")
    private String studentPassword;

    @Column(name = "course_name")
    private String studentCourse;

    // Constructors
        public Student() {
        }

        public Student(String studentName, String studentAddress, String studentEmail, String studentPassword, String studentCourse) {
            this.studentName = studentName;
            this.studentAddress = studentAddress;
            this.studentEmail = studentEmail;
            this.studentPassword = studentPassword;
            this.studentCourse = studentCourse;
        }

    public void setStudentId(int studentId) {
            this.studentId = studentId;
        }

    public void setStudentName(String studentName) {
            this.studentName = studentName;
        }

    public void setStudentAddress(String studentAddress) {
            this.studentAddress = studentAddress;
        }

    public void setStudentEmail(String studentEmail) {
            this.studentEmail = studentEmail;
        }

    public void setStudentPassword(String studentPassword) {
            this.studentPassword = studentPassword;
        }

    public void setStudentCourse(String studentCourse) {
        this.studentCourse = studentCourse;
    }

        @Override
        public String toString() {
            return "Student{" +
                    "studentId=" + studentId +
                    ", studentName='" + studentName + '\'' +
                    ", studentAddress='" + studentAddress + '\'' +
                    ", studentEmail='" + studentEmail + '\'' +
                    ", studentPassword='" + studentPassword + '\'' +
                    '}';
        }
    }