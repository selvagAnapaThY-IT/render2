package com.student.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.student.demo.model.Tutor1;
import com.student.demo.service.TutorService;
@CrossOrigin(origins="*")
@RestController
@RequestMapping("/tutors")
public class TutorController {

    @Autowired
    private TutorService tutorService;

    @PostMapping("/add")
    public ResponseEntity<String> addTutor(@RequestBody Tutor1 tutor) {
        tutorService.addTutor(tutor);
        return ResponseEntity.ok("tutor added successfully");
    }

}
