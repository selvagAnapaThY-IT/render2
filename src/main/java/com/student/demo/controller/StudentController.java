package com.student.demo.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.student.demo.model.Admin;
import com.student.demo.model.Hod;
import com.student.demo.model.Student;
import com.student.demo.model.Tutor1;
import com.student.demo.repository.AdminRepo;
import com.student.demo.repository.HodRepo;
import com.student.demo.repository.StudentRepository;
import com.student.demo.repository.TutorRepo;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class StudentController {

    @Autowired
    private StudentRepository studentRepo;
    
    

    @Autowired
    private TutorRepo tutorRepo;
    
    @Autowired
    private AdminRepo adminrepo;
    @Autowired
    private HodRepo hodrepo;

    // A common login API
    @PostMapping("/leaveform")
    
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> loginData) {
        String username = loginData.get("username");
        String password = loginData.get("password");

        // Check tutor login
        Optional<Tutor1> tutor = tutorRepo.findByUsername(username);
        if (tutor.isPresent() && tutor.get().getPassword().equals(password)) {
            return ResponseEntity.ok(Map.of("role", tutor.get().getRole()));
        }

        // Check student login
        Optional<Student> student = studentRepo.findByUsername(username);
        if (student.isPresent() && student.get().getPassword().equals(password)) {
            return ResponseEntity.ok(Map.of("role", student.get().getRole()));
        }
        Optional<Admin> admin = adminrepo.findByUsername(username);
        if (admin.isPresent() && admin.get().getPassword().equals(password)) {
            return ResponseEntity.ok(Map.of("role", admin.get().getRole()));
        }
        Optional<Hod> hod = hodrepo.findByUsername(username);
        if (hod.isPresent() && hod.get().getPassword().equals(password)) {
            return ResponseEntity.ok(Map.of("role", hod.get().getRole()));
        }
        

        // Invalid credentials
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        
    }
    @PostMapping("/students/add")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        Student savedStudent = studentRepo.save(student);
        return ResponseEntity.ok(savedStudent);
    }
    @GetMapping("/students/all")
    public ResponseEntity<?> getAllStudents() {
        return ResponseEntity.ok(studentRepo.findAll());
    }
}
