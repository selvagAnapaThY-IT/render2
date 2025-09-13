package com.student.demo.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.student.demo.repository.LeaveRequestRepository;

@Service
public class Leavecleanup {
	@Autowired
	LeaveRequestRepository leaverepo;
	 @Transactional
	@Scheduled(cron="0 0 0 * * ?",zone="Asia/Kolkata") // Runs daily at midnight
	public void deletereqbydatend() {
		LocalDate today1=LocalDate.now();
		leaverepo.deleteByToDateBefore(today1);
	}
}
