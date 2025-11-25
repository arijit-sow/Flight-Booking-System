package com.FlightBookingSystem.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.FlightBookingSystem.entity.Passenger;
import com.FlightBookingSystem.exception.RecordNotAvailableException;
import com.FlightBookingSystem.repository.PassengerRepository;

@Repository
public class PassengerDao {
	@Autowired
	private PassengerRepository passengerRepository;

	//Add passenger Records
	public Passenger addPassenger(Passenger passenger) {
		return passengerRepository.save(passenger);
	}

	// GET Passenger BY ID
	public Optional<Passenger> getPassengerById(int id) {
		return passengerRepository.findById(id);
	}

	// GET ALL THE Passenger RECORDS
	public List<Passenger> getAllPassenger(){
		return passengerRepository.findAll();
	}

	// UPDATE THE Passenger Record BY ID
	public Passenger updatePassenger(Passenger passenger) {
		return passengerRepository.save(passenger);
	}

	// DELETE  Passenger Record
	public Void deletePassenger(Passenger passenger) {
		passengerRepository.delete(passenger);
		return null;
	} 

	// GET Passenger BY Contact Number
	public Optional<Passenger> getPassengerByContactNumber(Long contactNumber) {
		return passengerRepository.findByContactNumber(contactNumber);
	}

	//CREATING THE PAGE AND SORTING TOGETHER
	public Page<Passenger> getPassengerByPageAndSorted(int pageNumber , int pageSize , String field){
		Page<Passenger> bookings = passengerRepository.findAll(PageRequest.of(pageNumber, pageSize ,Sort.by(field).descending()));
		if(!bookings.isEmpty()){
			return bookings;
		}
		else {
			throw new RecordNotAvailableException("No records are present");
		}
	}

}
