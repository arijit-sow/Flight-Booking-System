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
import com.FlightBookingSystem.entity.Passenger;
import com.FlightBookingSystem.services.PassengerService;

@RestController
@RequestMapping("/passenger")
public class PassengerController {
	@Autowired
	private PassengerService  passengerService;

	//Add passenger Records
	@PostMapping
	public ResponseEntity<ResponseStructure<Passenger>> addPassenger(@RequestBody Passenger passenger ){
		return passengerService.addPassenger(passenger);
	}

	// GET Passenger BY ID
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Passenger>> getPassengerByid(@PathVariable Integer id){
		return passengerService.getPassengerById(id);
	}

	// GET ALL THE Passenger RECORDS
	@GetMapping("/all")
	public ResponseEntity<ResponseStructure<List<Passenger>>> getAllPassenger(){
		return passengerService.getAllPassenger();
	}

	// UPDATE THE Passenger Record BY ID
	@PutMapping
	public ResponseEntity<ResponseStructure<Passenger>> updatePassenger(@RequestBody Passenger passenger){
		return passengerService.updatePassenger(passenger);
	}

	// DELETE  Passenger Record
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deletePassenger(@PathVariable Integer id ){
		return passengerService.deletePassenger(id);
	}

	// GET Passenger BY Contact Number
	@GetMapping("/contact/{contactNumber}")
	public ResponseEntity<ResponseStructure<Passenger>> getPassengerByContact(@PathVariable Long contactNumber){
		return passengerService.getPassengerByContactNumber(contactNumber);
	}

	// SORTING AND PAGING
	@GetMapping("/sortedpage/{pageNumber}/{pageSize}/{field}")
	public ResponseEntity<ResponseStructure<Page<Passenger>>> getBookByPagination(@PathVariable Integer pageNumber ,@PathVariable Integer  pageSize , @PathVariable String field){
		return passengerService.getPassengerByPageAndSorted(pageNumber, pageSize, field);
	}

}
