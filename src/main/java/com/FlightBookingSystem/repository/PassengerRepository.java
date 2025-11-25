package com.FlightBookingSystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.FlightBookingSystem.entity.Passenger;


public interface PassengerRepository extends JpaRepository<Passenger, Integer> {
	
	//Get Passenger by contact number
	Optional<Passenger> findByContactNumber(Long contactNumber);
	
	
}
