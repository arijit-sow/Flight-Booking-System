package com.FlightBookingSystem.controller;
import java.time.LocalDate;
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
import com.FlightBookingSystem.entity.Booking;
import com.FlightBookingSystem.entity.Passenger;
import com.FlightBookingSystem.entity.Payment;
import com.FlightBookingSystem.enums.BookingStatus;
import com.FlightBookingSystem.services.BookingService;

@RestController
@RequestMapping("/booking")
public class BookingController {
	@Autowired
	private BookingService bookingService;

	//save bookings
	@PostMapping
	public ResponseEntity<ResponseStructure<Booking>> createBooking(@RequestBody Booking booking){
		return bookingService.createBooking(booking);
	}
	
	// GET ALL THE Booking RECORDS
	@GetMapping("/all")
	public ResponseEntity<ResponseStructure<List<Booking>>> getAllBookings(){
		return bookingService.getAllBooking();
	}
	
	// GET Booking BY ID
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Booking>> getBookingById(@PathVariable Integer id){
		return bookingService.getBookingById(id);
	}
	
	// GET Booking BY Flight ID
	@GetMapping("/flight/{id}")
	public ResponseEntity<ResponseStructure<List<Booking>>> getBookingByFlightId(@PathVariable Integer id){
		return bookingService.getBookingByFlightId(id);
	}
	
	// GET ALL THE Booking By Date
	@GetMapping("/date/{date}")
	public ResponseEntity<ResponseStructure<List<Booking>>> getBookingByBookingDate(@PathVariable LocalDate date){
		return bookingService.getBookingByDate(date);
	}
	
	// GET ALL THE Booking By status
	@GetMapping("/booking/status/{status}")
	public ResponseEntity<ResponseStructure<List<Booking>>> getBookingByStatus(@PathVariable BookingStatus status){
		return bookingService.getBookingByStatus(status);
	}
	
	// GET ALL Passenger by Booking id
	@GetMapping("/passenger/{id}")
	public ResponseEntity<ResponseStructure<List<Passenger>>> getPassengerByBookingId(@PathVariable Integer id){
		return bookingService.getPassengerByBooking(id);
	}
	
	//GET ALL Payment by Booking id 
	@GetMapping("/payment/{id}")
	public ResponseEntity<ResponseStructure<Payment>> getPaymentByBookingId(@PathVariable Integer id){
		return bookingService.getPaymentByBookingId(id);
	}
	
	@PutMapping("/{id}/{status}")
	public ResponseEntity<ResponseStructure<Booking>> updateBooking(@PathVariable Integer id , @PathVariable BookingStatus status){
		return bookingService.updateBooking(id , status);
	}
	
	// DELETE THE Booking
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteBooking(@PathVariable Integer id){
		return bookingService.deleteBooking(id);
	}
	

	// SORTING AND PAGING
	@GetMapping("/sortedpage/{pageNumber}/{pageSize}/{field}")
	public ResponseEntity<ResponseStructure<Page<Booking>>> getBookByPagination(@PathVariable Integer pageNumber ,@PathVariable Integer  pageSize , @PathVariable String field){
			return bookingService.getBookByPageAndSorted(pageNumber, pageSize, field);
}
}
