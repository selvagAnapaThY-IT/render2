package com.student.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.demo.model.Student;

public interface StudentRepository extends JpaRepository<Student,Long> {
	Optional<Student>findByUsername(String username);
}
