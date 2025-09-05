package com.student.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.demo.model.LeaveRequest;

public interface HodLeaveRequest extends JpaRepository<LeaveRequest,Long> {
	List<LeaveRequest> findByStatus(String status);
}
