package com.student.demo.controller;



import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.student.demo.model.LeaveRequest;
import com.student.demo.service.LeaveRequestService;

@RestController
@RequestMapping("/leaveform")
@CrossOrigin(origins = "*")
public class LeaveController{

    @Autowired
    private LeaveRequestService service;

    // Insert Leave Request
    @PostMapping("/ins")
    public void insert(@RequestBody LeaveRequest request) {
        service.insertLeave(request);
    }

    // Update status (approve, reject, forward)
    
   
    @GetMapping("/pending")
    public List<LeaveRequest> getTutorPendingApprovals() {
        List<LeaveRequest> pending = service.getByStatus("PENDING");
        System.out.println("Fetched " + pending.size() + " pending requests");
        return pending;
    }
    @GetMapping("/approved-by-hod")
    public List<LeaveRequest> getApprovedByHod() {
        return service.getByStatus("HOD_APPROVED");
    }

    // Get all leave requests
    @GetMapping("/get")
    public List<LeaveRequest> select() {
        return service.getAllLeaves();
    }
   


    // Delete leave request
    @DeleteMapping("/del/{id}")
    public String delete(@PathVariable Long id) {
        return service.deleteLeave(id);
    }
 // Get only approved requests
    @GetMapping("/approved")
    public List<LeaveRequest> getApproved() {
        return service.getByStatus("APPROVED_BY_TUTOR");
    }
 // Get leave requests forwarded to HOD
    @GetMapping("/forwarded")
    public List<LeaveRequest> getForwardedRequests() {
        return service.getByStatus("FORWARDED_TO_HOD");
    }
    @GetMapping("/search")
    public ResponseEntity<List<LeaveRequest>> getLeaveByRollNo(@RequestParam String rollNumber) {
        List<LeaveRequest> list = service.getByRollNumber(rollNumber);
        if (list.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(list);
    }

    // Get only pending requests (for tutor)
    @GetMapping("/tutor/approved-not-forwarded")
    public List<LeaveRequest> getApprovedNotForwarded() {
        return service.getByStatus("APPROVED_BY_TUTOR");
    }
    @GetMapping("/rejectedbyhod")
    public List<LeaveRequest> getRejectedRequests() {
        return service.getByStatus("HOD_REJECTED");
        
    }
    @GetMapping("/rejectedbytut")
    public List<LeaveRequest> getRejectedRequests2() {
        return service.getByStatus("REJECTED_BY_TUTOR");
        
    }
    

    @PutMapping("/upd/{id}")
    public ResponseEntity<String> updateStatus(@PathVariable Long id, @RequestParam String action) {
        boolean updated = false;

        switch (action.toLowerCase()) {
            case "approve":
                updated = service.updateLeaveStatus(id, "APPROVED_BY_TUTOR");
                break;
            case "reject":
                updated = service.updateLeaveStatus(id, "REJECTED_BY_TUTOR");
                break;
            case "forward":
                updated = service.forwardLeaveToHod(id);
                break;
            default:
                return ResponseEntity.badRequest().body("Invalid action");
        }
        

        if (updated) {
            return ResponseEntity.ok("Leave updated");
        } else {
            return ResponseEntity.status(500).body("Update failed");
        }
    }
    
    
    


}
