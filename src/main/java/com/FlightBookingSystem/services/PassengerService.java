package com.FlightBookingSystem.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.FlightBookingSystem.dao.PassengerDao;
import com.FlightBookingSystem.dto.ResponseStructure;
import com.FlightBookingSystem.entity.Passenger;
import com.FlightBookingSystem.exception.IdNotFoundException;
import com.FlightBookingSystem.exception.RecordNotAvailableException;

@Service
public class PassengerService {
	@Autowired
	private PassengerDao passengerDao;

	//Add passenger Records
	public ResponseEntity<ResponseStructure<Passenger>> addPassenger(Passenger passenger) {
		ResponseStructure<Passenger> response = new ResponseStructure<Passenger>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Passenger Information is Successfull added");
		response.setData(passengerDao.addPassenger(passenger));
		return new ResponseEntity<ResponseStructure<Passenger>>(response, HttpStatus.CREATED);
	}

	// GET Passenger BY ID
	public ResponseEntity<ResponseStructure<Passenger>> getPassengerById(int id) {
		ResponseStructure<Passenger> response = new ResponseStructure<Passenger>();
		Optional<Passenger> opt = passengerDao.getPassengerById(id);
		if (opt.isPresent()) {
			response.setStatusCode(HttpStatus.FOUND.value());
			response.setMessage("Passenger record  is fetched using id");
			response.setData(opt.get());
			return new ResponseEntity<ResponseStructure<Passenger>>(response, HttpStatus.FOUND);
		} else {
			throw new RecordNotAvailableException("Passenger Records are not avilable");
		}

	}

	// GET ALL THE Passenger RECORDS
	public ResponseEntity<ResponseStructure<List<Passenger>>> getAllPassenger() {
		ResponseStructure<List<Passenger>> response = new ResponseStructure<List<Passenger>>();
		List<Passenger> passenger = passengerDao.getAllPassenger();
		if (!passenger.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Found All Passengers records");
			response.setData(passenger);
			return new ResponseEntity<ResponseStructure<List<Passenger>>>(response, HttpStatus.OK);
		} else {
			throw new RecordNotAvailableException("Passenger is not available");
		}
	}

	// UPDATE THE Passenger Record BY ID
	public ResponseEntity<ResponseStructure<Passenger>> updatePassenger(Passenger passenger) {
		ResponseStructure<Passenger> response = new ResponseStructure<Passenger>();
		if (passenger.getId() == null) {
			throw new IdNotFoundException("Id must be Passed");
		}
		Optional<Passenger> opt = passengerDao.getPassengerById(passenger.getId());
		if (opt.isPresent()) {
			response.setStatusCode(HttpStatus.CREATED.value());
			response.setMessage("Passenger Record is Updated of Passenger id " + passenger.getId());
			response.setData(passengerDao.updatePassenger(passenger));
			return new ResponseEntity<ResponseStructure<Passenger>>(response, HttpStatus.FOUND);
		} else {
			throw new RecordNotAvailableException("Passenger Data is not Available");
		}

	}

	// DELETE  Passenger Record
	public ResponseEntity<ResponseStructure<String>> deletePassenger(Integer id) {
		ResponseStructure<String> response = new ResponseStructure<String>();
		Optional<Passenger> opt = passengerDao.getPassengerById(id);
		if (opt.isPresent()) {
			passengerDao.deletePassenger(opt.get());
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Passenger Record is Deleted");
			response.setData("Success");
			return new ResponseEntity<ResponseStructure<String>>(response, HttpStatus.OK);
		} else {

			throw new RecordNotAvailableException("Record is Not Availlable");

		}
	}

	// GET Passenger BY Contact Number
	public ResponseEntity<ResponseStructure<Passenger>> getPassengerByContactNumber(Long contactNumber) {
		ResponseStructure<Passenger> response = new ResponseStructure<Passenger>();
		Optional<Passenger> opt = passengerDao.getPassengerByContactNumber(contactNumber);
		if (opt.isPresent()) {
			response.setStatusCode(HttpStatus.FOUND.value());
			response.setMessage("Passenger record  is fetched using id");
			response.setData(opt.get());
			return new ResponseEntity<ResponseStructure<Passenger>>(response, HttpStatus.FOUND);
		} else {
			throw new RecordNotAvailableException("Passenger Records are not avilable");
		}
	}

	// SORTING AND PAGING THE RECORDS
	public ResponseEntity<ResponseStructure<Page<Passenger>>> getPassengerByPageAndSorted(int pageNumber, int pageSize,
			String field) {
		ResponseStructure<Page<Passenger>> response = new ResponseStructure<Page<Passenger>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Passenger Record is found in sorted and page form");
		response.setData(passengerDao.getPassengerByPageAndSorted(pageNumber, pageSize, field));
		return new ResponseEntity<ResponseStructure<Page<Passenger>>>(response, HttpStatus.OK);
	}

}
