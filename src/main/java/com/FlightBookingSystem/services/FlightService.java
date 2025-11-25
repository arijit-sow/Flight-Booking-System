package com.FlightBookingSystem.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.FlightBookingSystem.dao.FlightDao;
import com.FlightBookingSystem.dto.ResponseStructure;
import com.FlightBookingSystem.entity.Flight;
import com.FlightBookingSystem.exception.FlightNotFoundException;
import com.FlightBookingSystem.exception.IdNotFoundException;

@Service
public class FlightService {
	@Autowired
	private FlightDao flightdao;

	//Add Flight Record
	public ResponseEntity<ResponseStructure<Flight>> addFlightEntity(Flight flight) {
		ResponseStructure<Flight> response = new ResponseStructure<Flight>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Flight Information is Successfull added");
		response.setData(flightdao.addFlight(flight));
		return new ResponseEntity<ResponseStructure<Flight>>(response, HttpStatus.OK);
	}

	// GET Flight BY ID
	public ResponseEntity<ResponseStructure<Flight>> getFlightById(int id) {
		ResponseStructure<Flight> response = new ResponseStructure<Flight>();
		Optional<Flight> opt = flightdao.getFlighById(id);
		if (opt.isPresent()) {
			response.setStatusCode(HttpStatus.FOUND.value());
			response.setMessage("Flight is fetched using id");
			response.setData(opt.get());
			return new ResponseEntity<ResponseStructure<Flight>>(response, HttpStatus.FOUND);
		} else {
			throw new FlightNotFoundException("Flight Records are not avilable");
		}

	}

	// GET ALL THE Flight RECORDS
	public ResponseEntity<ResponseStructure<List<Flight>>> getAllFlight() {
		ResponseStructure<List<Flight>> response = new ResponseStructure<List<Flight>>();
		List<Flight> flight = flightdao.getAllFlight();
		if (!flight.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Found All data");
			response.setData(flightdao.getAllFlight());
			return new ResponseEntity<ResponseStructure<List<Flight>>>(response, HttpStatus.OK);
		} else {
			throw new FlightNotFoundException("Flight is not available");
		}

	}

	// UPDATE THE Flight BY ID
	public ResponseEntity<ResponseStructure<Flight>> updateFlight(Flight flight) {
		ResponseStructure<Flight> response = new ResponseStructure<Flight>();
		if (flight.getId() == null) {
			throw new IdNotFoundException("Id must be Passed");
		}
		Optional<Flight> opt = flightdao.getFlighById(flight.getId());
		if (opt.isPresent()) {
			response.setStatusCode(HttpStatus.CREATED.value());
			response.setMessage("Flight Record is Updated of flight id " + flight.getId());
			response.setData(opt.get());
			return new ResponseEntity<ResponseStructure<Flight>>(response, HttpStatus.FOUND);
		} else {
			throw new FlightNotFoundException("Flight Data is not Available");
		}

	}

	// DELETE THE Flight
	public ResponseEntity<ResponseStructure<String>> deleteFlight(Integer id) {
		ResponseStructure<String> response = new ResponseStructure<String>();
		Optional<Flight> opt = flightdao.getFlighById(id);
		if (opt.isPresent()) {
			flightdao.deleteFlight(opt.get());
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Flight Record is Deleted");
			response.setData("Success");
			return new ResponseEntity<ResponseStructure<String>>(response, HttpStatus.OK);
		} else {

			throw new IdNotFoundException("Id Not Availlable");

		}
	}

	// GET FLIGHT BY SOURCE AND DESTINATION
	public ResponseEntity<ResponseStructure<List<Flight>>> getFlightBySourceAndDestination(String source,
			String Destination) {
		ResponseStructure<List<Flight>> response = new ResponseStructure<List<Flight>>();
		List<Flight> flight = flightdao.getFlightBySourceAndDestination(source, Destination);
		if (!flight.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Found All the Flight by Given Route");
			response.setData(flight);
			return new ResponseEntity<ResponseStructure<List<Flight>>>(response, HttpStatus.OK);
		} else {
			throw new FlightNotFoundException("Flight is not available");
		}

	}

	// GET FLIGHT BY AIRLINE
	public ResponseEntity<ResponseStructure<List<Flight>>> getFlightByAirline(String airline) {
		ResponseStructure<List<Flight>> response = new ResponseStructure<List<Flight>>();
		List<Flight> flight = flightdao.getFlightByAirline(airline);
		if (!flight.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Found All the Flight by Given Airline ");
			response.setData(flight);
			return new ResponseEntity<ResponseStructure<List<Flight>>>(response, HttpStatus.OK);
		} else {
			throw new FlightNotFoundException("Flight is not available");
		}
	}

	// SORTING AND PAGING THE RECORDS
	public ResponseEntity<ResponseStructure<Page<Flight>>> getFlightByPageAndSorted(int pageNumber, int pageSize,String field) {
		ResponseStructure<Page<Flight>> response = new ResponseStructure<Page<Flight>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Flight Record is found in sorted and page form");
		response.setData(flightdao.getFlightByPageAndSorted(pageNumber, pageSize, field));
		return new ResponseEntity<ResponseStructure<Page<Flight>>>(response, HttpStatus.OK);
	}
}
