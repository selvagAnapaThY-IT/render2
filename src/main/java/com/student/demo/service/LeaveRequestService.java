package com.student.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.demo.model.LeaveRequest;
import com.student.demo.repository.LeaveRequestRepository;

import jakarta.transaction.Transactional;

@Service
public class LeaveRequestService {

    @Autowired
    private LeaveRequestRepository repo;

    public void insertLeave(LeaveRequest req) {
        repo.save(req);
    }

    public boolean updateLeaveStatus(Long id, String newStatus) {
        Optional<LeaveRequest> optional = repo.findById(id);
        if (optional.isPresent()) {
            LeaveRequest request = optional.get();
            request.setStatus(newStatus);
            repo.save(request);
            return true;
        }
        return false;
    }
    @Transactional
    public boolean forwardAllApprovedToHod() {
        List<LeaveRequest> approvedRequests = repo.findByStatus("APPROVED_BY_TUTOR");

        if (approvedRequests.isEmpty()) {
            System.out.println("No approved requests to forward.");
            return false;
        }

        for (LeaveRequest request : approvedRequests) {
            request.setStatus("FORWARDED_TO_HOD");
        }

        repo.saveAll(approvedRequests);
        System.out.println("Forwarded " + approvedRequests.size() + " requests to HOD.");
        return true;
    }

    public List<LeaveRequest> getAllLeaves() {
        return repo.findAll();
    }

    public String deleteLeave(Long id) {
        repo.deleteById(id);
        return "Deleted";
    }

    public List<LeaveRequest> getByStatus(String status) {
        return repo.findByStatus(status);
    }
    public boolean updateRequest(Long id, LeaveRequest updatedRequest) {
        LeaveRequest existingRequest = repo.findById(id).orElse(null);
        if (existingRequest == null) {
            return false;
        }

        existingRequest.setStatus(updatedRequest.getStatus());
        existingRequest.setStudentName(updatedRequest.getStudentName());
        existingRequest.setRollNumber(updatedRequest.getRollNumber());
        existingRequest.setCourse(updatedRequest.getCourse());
        existingRequest.setBranch(updatedRequest.getBranch());
        existingRequest.setSection(updatedRequest.getSection());
        existingRequest.setFromDate(updatedRequest.getFromDate());
        existingRequest.setToDate(updatedRequest.getToDate());
        existingRequest.setTotalDays(updatedRequest.getTotalDays());
        existingRequest.setReason(updatedRequest.getReason());
        existingRequest.setParentName(updatedRequest.getParentName());
        existingRequest.setRelationship(updatedRequest.getRelationship());
        existingRequest.setPhone(updatedRequest.getPhone());

        repo.save(existingRequest);
        return true;
    }
    public List<LeaveRequest> getByRollNumber(String rollNumber) {
        return repo.findByRollNumber(rollNumber);
    }



    public boolean forwardLeaveToHod(Long id) {
        Optional<LeaveRequest> opt = repo.findById(id);
        if (opt.isPresent()) {
            LeaveRequest leave = opt.get();
            leave.setStatus("FORWARDED_TO_HOD");  // 🔁 Consistent status
            repo.save(leave);
            return true;
        }
        return false;
    }
}
