package com.FlightBookingSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.FlightBookingSystem.entity.Payment;
import com.FlightBookingSystem.enums.Mode;
import com.FlightBookingSystem.enums.PaymentStatus;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
	
	// GET Payment Record By Status
	List<Payment> findByStatus(PaymentStatus status);
	
	// GET Payment Record By mode 
	List<Payment> findByMode(Mode mode);
	
	
}
