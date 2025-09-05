package com.student.demo.service;

import com.student.demo.model.LeaveRequest;
import com.student.demo.repository.LeaveRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HodleaveRequestService {
	


    @Autowired
    private LeaveRequestRepository repository;

    public List<LeaveRequest> getRequestsByStatus(String status) {
        return repository.findByStatus(status);
    }
    public List<LeaveRequest> getForwardedRequests() {
        return repository.findByStatus("FORWARDED_TO_HOD");
    }


    public boolean updateStatus(Long id, String action) {
        LeaveRequest request = repository.findById(id).orElse(null);
        if (request == null) return false;

        switch (action.toLowerCase()) {
            case "approve":
                request.setStatus("HOD_APPROVED");
                break;
            case "reject":
                request.setStatus("HOD_REJECTED");
                break;
            case "forward":
                request.setStatus("FORWARDED_TO_HOD");
                break;
            default:
                return false;
        }
        repository.save(request);
        return true;
    }
    public boolean updateRequest(Long id, LeaveRequest updatedRequest) {
        LeaveRequest existingRequest = repository.findById(id).orElse(null);
        if (existingRequest == null) {
            return false;
        }

        // Update fields (you can customize this as needed)
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

        repository.save(existingRequest);
        return true;
    }

    public boolean forwardAllApprovedToHod() {
        List<LeaveRequest> approved = repository.findByStatus("APPROVED_BY_TUTOR");
        for (LeaveRequest r : approved) {
            r.setStatus("FORWARDED_TO_HOD");
        }
        repository.saveAll(approved);
        return true;
    }
}