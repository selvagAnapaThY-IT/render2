package com.student.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.demo.model.Hod;


public interface HodRepo extends JpaRepository<Hod,Long> {
	Optional<Hod>findByUsername(String username);
}
