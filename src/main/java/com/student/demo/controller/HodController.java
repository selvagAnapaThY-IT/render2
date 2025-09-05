package com.student.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.student.demo.model.Hod;
import com.student.demo.model.LeaveRequest;

import com.student.demo.repository.HodRepo;
import com.student.demo.repository.LeaveRequestRepository;
import com.student.demo.service.HodleaveRequestService;

@RestController
@RequestMapping("/hodform")
@CrossOrigin(origins = "*")
public class HodController {
	 @Autowired
	    private HodleaveRequestService service;
	 @Autowired
	 	private HodRepo hodrepo;
	 @Autowired
	    private LeaveRequestRepository leaveRequestRepository;
	 @PutMapping("/hod/upd/{id}")
	    public ResponseEntity<String> updateStatus(@PathVariable Long id, @RequestParam String action) {
	        LeaveRequest req = leaveRequestRepository.findById(id).orElse(null);
	        if (req == null) return ResponseEntity.notFound().build();

	        switch (action.toLowerCase()) {
	            case "approve":
	                req.setStatus("HOD_APPROVED");  // ✅ Set final approval status
	                break;
	            case "reject":
	                req.setStatus("HOD_REJECTED");
	                break;
	            default:
	                return ResponseEntity.badRequest().body("Invalid action");
	        }

	        leaveRequestRepository.save(req);
	        return ResponseEntity.ok("Request " + action + "d successfully.");
	    }
	 @GetMapping("/approved")
	 public List<LeaveRequest> getApprovedRequests() {
	     return leaveRequestRepository.findByStatus("APROVED_BY_TUTOR");
	 }
	 @PostMapping("/addhod")
	    public ResponseEntity<Hod> addHod(@RequestBody Hod hod) {
	        Hod savedHod = hodrepo.save(hod); // ✅ Correct: use the instance
	        return ResponseEntity.ok(savedHod);
	    }
	    @GetMapping("/requests")
	    public List<LeaveRequest> getForwardedRequests() {
	        return service.getForwardedRequests();
	    }
	    @GetMapping("/viewhod")
	    public List<Hod> getAllHods() {
	        return hodrepo.findAll();
	    }
	    @PutMapping("/forwardtohod")
	    public ResponseEntity<String> forwardAllApprovedToHod() {
	        boolean success = service.forwardAllApprovedToHod();
	        if (success) {
	            return ResponseEntity.ok("Approved requests forwarded to HOD.");
	        } else {
	            return ResponseEntity.badRequest().body("No approved requests found.");
	        }
	    }
	    @PutMapping("/updatehod/{id}")
	    public ResponseEntity<Hod> updateHod(@PathVariable Long id, @RequestBody Hod updatedHod) {
	        return hodrepo.findById(id).map(existingHod -> {
	            existingHod.setName(updatedHod.getName()); // 👈 update name
	            existingHod.setUsername(updatedHod.getUsername()); // 👈 if needed
	            existingHod.setPassword(updatedHod.getPassword()); // 👈 optional
	            existingHod.setDepartment(updatedHod.getDepartment()); // 👈 optional

	            Hod savedHod = hodrepo.save(existingHod);
	            return ResponseEntity.ok(savedHod);
	        }).orElseGet(() -> ResponseEntity.notFound().build());
	    }

	    
	    @PutMapping("/edit/{id}")
	    public boolean editRequest(@PathVariable Long id, @RequestBody LeaveRequest updated) {
	        return service.updateRequest(id, updated);
	    }
	   
	    
}
