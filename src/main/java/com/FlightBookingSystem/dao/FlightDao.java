package com.FlightBookingSystem.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.FlightBookingSystem.entity.Flight;
import com.FlightBookingSystem.exception.RecordNotAvailableException;
import com.FlightBookingSystem.repository.FlightRepository;

@Repository
public class FlightDao {
	@Autowired
	private FlightRepository flightRepository;
	
	//Add Flight Record
	public Flight addFlight(Flight flight) {
		return flightRepository.save(flight);
	}
	
	// GET Flight BY ID
	public Optional<Flight> getFlighById(int id) {
		return flightRepository.findById(id);
	}
	
	// GET ALL THE Flight RECORDS
	public List<Flight> getAllFlight(){
		return flightRepository.findAll();
	}
	
	// UPDATE THE Flight BY ID
	public Flight updateFlight(Flight flight) {
		return flightRepository.save(flight);
	}
	
	// DELETE THE Flight
	public Void deleteFlight(Flight flight) {
		flightRepository.delete(flight);
		return null;
	}
	
	// GET FLIGHT BY SOURCE AND DESTINATION
	public List<Flight> getFlightBySourceAndDestination(String source , String destination){
		return flightRepository.findBySourceAndDestination(source, destination);
	}
	
	// GET FLIGHT BY AIRLINE
	public List<Flight> getFlightByAirline(String airline){
		return flightRepository.findByAirline(airline);
	}
	

	// CREATING THE PAGE AND SORTING TOGETHER
	public Page<Flight> getFlightByPageAndSorted(int pageNumber, int pageSize, String field) {
		Page<Flight> flights = flightRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(field).descending()));
		if (!flights.isEmpty()) {
			return flights;
		} else {
			throw new RecordNotAvailableException("No records are present");
		}
	}
	
}

