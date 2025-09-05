package com.student.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.demo.model.Admin;

public interface AdminRepo extends JpaRepository<Admin,Long>{
	Optional<Admin>findByUsername(String username);
}
