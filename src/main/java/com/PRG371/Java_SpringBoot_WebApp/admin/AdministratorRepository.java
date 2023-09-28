package com.PRG371.Java_SpringBoot_WebApp.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {
    Optional<Administrator> findByAdminNameAndPassword(String adminName, String password);

}
