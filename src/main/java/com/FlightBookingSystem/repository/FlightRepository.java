package com.FlightBookingSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.FlightBookingSystem.entity.Flight;

public interface FlightRepository extends JpaRepository<Flight, Integer> {
		
	// GET FLIGHT BY SOURCE AND DESTINATION
	List<Flight> findBySourceAndDestination(String source , String destination);
	
	// GET FLIGHT BY AIRLINE
	List<Flight> findByAirline(String airline);
	
}
