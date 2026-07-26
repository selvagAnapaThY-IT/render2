package com.student.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tutors1")

public class Tutor1 {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 private String username;
	 private String password;
	 private String role;
	 private String name;
	 private String department;
	 private String email;
	 private String phone;
	 private String section;

	 public Long getId() {
		 return id;
	 }
	 public void setId(Long id) {
		 this.id = id;
	 }
	 public String getUsername() {
		 return username;
	 }
	 public void setUsername(String username) {
		 this.username = username;
	 }
	 public String getPassword() {
		 return password;
	 }
	 public void setPassword(String password) {
		 this.password = password;
	 }
	 public String getRole() {
		 return role;
	 }
	 public void setRole(String role) {
		 this.role = role;
	 }
	 
	 public String getName() {
		return name;
	}
	 public void setName(String name) {
		 this.name = name;
	 }
	 public String getDepartment() {
		 return department;
	 }
	 public void setDepartment(String department) {
		 this.department = department;
	 }
	 public String getEmail() {
		 return email;
	 }
	 public void setEmail(String email) {
		 this.email = email;
	 }
	 public String getPhone() {
		 return phone;
	 }
	 public void setPhone(String phone) {
		 this.phone = phone;
	 }
	 public String getSection() {
		 return section;
	 }
	 public void setSection(String section) {
		 this.section = section;
	 }

	 public Tutor1() {
		
	 }
	 
	
}
