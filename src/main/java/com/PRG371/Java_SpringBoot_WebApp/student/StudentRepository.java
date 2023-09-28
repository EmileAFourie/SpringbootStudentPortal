package com.PRG371.Java_SpringBoot_WebApp.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Query("SELECT s FROM Student s WHERE s.studentEmail = ?1")
    Optional<Student> findStudentByEmail(String email);

    @Query("SELECT s FROM Student s WHERE s.studentEmail = ?1 AND s.studentPassword = ?2")
    Optional<Student> findByEmailAndPassword(String email, String password);

    @Query("SELECT s FROM Student s WHERE s.studentEmail = ?1 AND s.studentPassword = ?2")
    Optional<Student> findByEmailWithQuery(@Param("studentEmail") String email);
}