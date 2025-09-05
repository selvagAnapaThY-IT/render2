package com.student.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.student.demo.model.Tutor1;
import com.student.demo.repository.TutorRepo;

@Service
public class TutorService {
    
    @Autowired
    private TutorRepo tutorRepository;

    public void addTutor(Tutor1 tutor) {
        tutorRepository.save(tutor);
    }
}
