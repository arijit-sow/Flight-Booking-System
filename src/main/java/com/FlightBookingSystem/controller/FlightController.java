package com.FlightBookingSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.FlightBookingSystem.dto.ResponseStructure;
import com.FlightBookingSystem.entity.Flight;
import com.FlightBookingSystem.services.FlightService;

@RestController
@RequestMapping("/flight")
public class FlightController {
	@Autowired
	private FlightService flightService;

	//Add Flight Record
	@PostMapping
	public ResponseEntity<ResponseStructure<Flight>> addFlight(@RequestBody Flight flight) {
		return flightService.addFlightEntity(flight);
	}

	// GET Flight BY ID
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Flight>> getFlightByid(@PathVariable Integer id) {
		return flightService.getFlightById(id);
	}

	// GET ALL THE Flight RECORDS
	@GetMapping("/all")
	public ResponseEntity<ResponseStructure<List<Flight>>> getAllFlight() {
		return flightService.getAllFlight();
	}

	// UPDATE THE Flight BY ID
	@PutMapping
	public ResponseEntity<ResponseStructure<Flight>> updateFlight(@RequestBody Flight flight) {
		return flightService.updateFlight(flight);
	}

	// DELETE THE Flight
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteFlight(@PathVariable Integer id) {
		return flightService.deleteFlight(id);
	}

	// GET FLIGHT BY SOURCE AND DESTINATION
	@GetMapping("/{source}/{destination}")
	public ResponseEntity<ResponseStructure<List<Flight>>> getflightBySourceAndDestination(@PathVariable String source,
			@PathVariable String destination) {
		return flightService.getFlightBySourceAndDestination(source, destination);
	}

	// GET FLIGHT BY AIRLINE
	@GetMapping("/airline/{airline}")
	public ResponseEntity<ResponseStructure<List<Flight>>> getflightByAirline(@PathVariable String airline) {
		return flightService.getFlightByAirline(airline);
	}
	
	// SORTING AND PAGING
		@GetMapping("/sortedpage/{pageNumber}/{pageSize}/{field}")
		public ResponseEntity<ResponseStructure<Page<Flight>>> getFlightByPagination(@PathVariable Integer pageNumber ,@PathVariable Integer  pageSize , @PathVariable String field){
				return flightService.getFlightByPageAndSorted(pageNumber, pageSize, field);
	}

}
