package com.student.demo.controller;

import java.util.List;
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

    // A common login API supporting username or email for all roles
    @PostMapping("/leaveform")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> loginData) {
        String input = loginData.get("username");
        String password = loginData.get("password");

        // 1. Check tutor login (by username or email)
        Optional<Tutor1> tutorOpt = tutorRepo.findByUsername(input);
        if (tutorOpt.isEmpty()) {
            tutorOpt = tutorRepo.findByEmail(input);
        }
        if (tutorOpt.isPresent() && tutorOpt.get().getPassword().equals(password)) {
            Tutor1 t = tutorOpt.get();
            Map<String, Object> resp = new java.util.HashMap<>();
            resp.put("role", t.getRole() != null ? t.getRole() : "tutor");
            resp.put("id", t.getId());
            resp.put("name", t.getName() != null ? t.getName() : "");
            resp.put("username", t.getUsername() != null ? t.getUsername() : "");
            resp.put("email", t.getEmail() != null ? t.getEmail() : "");
            resp.put("phone", t.getPhone() != null ? t.getPhone() : "");
            resp.put("department", t.getDepartment() != null ? t.getDepartment() : "");
            resp.put("section", t.getSection() != null ? t.getSection() : "");
            return ResponseEntity.ok(resp);
        }

        // 2. Check HOD login (by username or email)
        Optional<Hod> hodOpt = hodrepo.findByUsername(input);
        if (hodOpt.isEmpty()) {
            hodOpt = hodrepo.findByEmail(input);
        }
        if (hodOpt.isPresent() && hodOpt.get().getPassword().equals(password)) {
            Hod h = hodOpt.get();
            Map<String, Object> resp = new java.util.HashMap<>();
            resp.put("role", h.getRole() != null ? h.getRole() : "hod");
            resp.put("id", h.getId());
            resp.put("name", h.getName() != null ? h.getName() : "");
            resp.put("username", h.getUsername() != null ? h.getUsername() : "");
            resp.put("email", h.getEmail() != null ? h.getEmail() : "");
            resp.put("phone", h.getPhone() != null ? h.getPhone() : "");
            resp.put("department", h.getDepartment() != null ? h.getDepartment() : "");
            return ResponseEntity.ok(resp);
        }

        // 3. Check Admin login
        Optional<Admin> admin = adminrepo.findByUsername(input);
        if (admin.isPresent() && admin.get().getPassword().equals(password)) {
            Admin a = admin.get();
            Map<String, Object> resp = new java.util.HashMap<>();
            resp.put("role", a.getRole() != null ? a.getRole() : "admin");
            resp.put("username", a.getUsername() != null ? a.getUsername() : "");
            return ResponseEntity.ok(resp);
        }

        // 4. Check student login (by username, rollno, or email)
        Optional<Student> studentOpt = studentRepo.findByUsername(input);
        if (studentOpt.isEmpty()) {
            studentOpt = studentRepo.findByRollno(input);
        }
        if (studentOpt.isEmpty()) {
            studentOpt = studentRepo.findByEmail(input);
        }

        if (studentOpt.isPresent() && studentOpt.get().getPassword().equals(password)) {
            Student st = studentOpt.get();
            Map<String, Object> resp = new java.util.HashMap<>();
            resp.put("role", st.getRole() != null ? st.getRole() : "student");
            resp.put("username", st.getUsername() != null ? st.getUsername() : "");
            resp.put("rollno", st.getRollno() != null ? st.getRollno() : "");
            resp.put("department", st.getDepartment() != null ? st.getDepartment() : "");
            resp.put("name", st.getName() != null ? st.getName() : "");
            resp.put("course", st.getCourse() != null ? st.getCourse() : "");
            resp.put("branch", st.getBranch() != null ? st.getBranch() : "");
            resp.put("section", st.getSection() != null ? st.getSection() : "");
            resp.put("email", st.getEmail() != null ? st.getEmail() : "");
            resp.put("phone", st.getPhone() != null ? st.getPhone() : "");
            resp.put("parentName", st.getParentName() != null ? st.getParentName() : "");
            resp.put("parentPhone", st.getParentPhone() != null ? st.getParentPhone() : "");
            return ResponseEntity.ok(resp);
        }

        // Invalid credentials
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/students/add")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        if (student.getRollno() == null || student.getRollno().isEmpty()) {
            student.setRollno(student.getUsername());
        }
        Student savedStudent = studentRepo.save(student);
        return ResponseEntity.ok(savedStudent);
    }

    @GetMapping("/students/all")
    public ResponseEntity<?> getAllStudents() {
        return ResponseEntity.ok(studentRepo.findAll());
    }

    @GetMapping("/students/profile")
    public ResponseEntity<?> getStudentProfile(@RequestParam String rollno) {
        Optional<Student> student = studentRepo.findByRollno(rollno);
        if (student.isEmpty()) {
            student = studentRepo.findByUsername(rollno);
        }
        if (student.isPresent()) {
            return ResponseEntity.ok(student.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student profile not found");
    }

    @PostMapping("/students/profile/update")
    public ResponseEntity<?> updateStudentProfile(@RequestBody Student updatedStudent) {
        String identifier = updatedStudent.getRollno() != null ? updatedStudent.getRollno() : updatedStudent.getUsername();
        Optional<Student> opt = studentRepo.findByRollno(identifier);
        if (opt.isEmpty()) {
            opt = studentRepo.findByUsername(identifier);
        }
        if (opt.isPresent()) {
            Student existing = opt.get();
            if (updatedStudent.getName() != null) existing.setName(updatedStudent.getName());
            if (updatedStudent.getDepartment() != null) existing.setDepartment(updatedStudent.getDepartment());
            if (updatedStudent.getCourse() != null) existing.setCourse(updatedStudent.getCourse());
            if (updatedStudent.getBranch() != null) existing.setBranch(updatedStudent.getBranch());
            if (updatedStudent.getSection() != null) existing.setSection(updatedStudent.getSection());
            if (updatedStudent.getEmail() != null) existing.setEmail(updatedStudent.getEmail());
            if (updatedStudent.getPhone() != null) existing.setPhone(updatedStudent.getPhone());
            if (updatedStudent.getParentName() != null) existing.setParentName(updatedStudent.getParentName());
            if (updatedStudent.getParentPhone() != null) existing.setParentPhone(updatedStudent.getParentPhone());
            if (updatedStudent.getPassword() != null && !updatedStudent.getPassword().isEmpty()) existing.setPassword(updatedStudent.getPassword());
            
            Student saved = studentRepo.save(existing);
            return ResponseEntity.ok(saved);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found for update");
    }

    @GetMapping("/students/section")
    public ResponseEntity<?> getStudentsBySection(@RequestParam String section) {
        List<Student> list = studentRepo.findAll().stream()
                .filter(s -> s.getSection() != null && s.getSection().equalsIgnoreCase(section))
                .toList();
        return ResponseEntity.ok(list);
    }
}
