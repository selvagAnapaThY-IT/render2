package com.student.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.demo.model.Tutor1;

public interface TutorRepo extends JpaRepository<Tutor1,Long> {
		Optional<Tutor1>findByUsername(String username);
}
