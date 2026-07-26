package com.student.demo.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//Student.java
@Entity
@Table(name="students1")
public class Student {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 private String username;
 private String password;
 private String role;
 private String rollno;
 private String department;
 private String name;
 private String course;
 private String branch;
 private String section;
 private String email;
 private String phone;
 private String parentName;
 private String parentPhone;

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
 
 public String getRollno() {
	return rollno;
}
 public void setRollno(String rollno) {
	this.rollno = rollno;
 }
 public String getDepartment() {
	return department;
 }
 public void setDepartment(String department) {
	this.department = department;
 }

 public String getName() {
	return name;
 }
 public void setName(String name) {
	this.name = name;
 }
 public String getCourse() {
	return course;
 }
 public void setCourse(String course) {
	this.course = course;
 }
 public String getBranch() {
	return branch;
 }
 public void setBranch(String branch) {
	this.branch = branch;
 }
 public String getSection() {
	return section;
 }
 public void setSection(String section) {
	this.section = section;
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
 public String getParentName() {
	return parentName;
 }
 public void setParentName(String parentName) {
	this.parentName = parentName;
 }
 public String getParentPhone() {
	return parentPhone;
 }
 public void setParentPhone(String parentPhone) {
	this.parentPhone = parentPhone;
 }

 public Student() {

 }
}

