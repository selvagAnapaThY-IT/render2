package com.student.demo.repository;



import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.student.demo.model.LeaveRequest;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    List<LeaveRequest> findByStatus(String status);
    List<LeaveRequest> findByRollNumber(String rollNumber);

}


