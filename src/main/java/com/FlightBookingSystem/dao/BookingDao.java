package com.FlightBookingSystem.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.FlightBookingSystem.entity.Booking;
import com.FlightBookingSystem.entity.Passenger;
import com.FlightBookingSystem.entity.Payment;
import com.FlightBookingSystem.enums.BookingStatus;
import com.FlightBookingSystem.exception.RecordNotAvailableException;
import com.FlightBookingSystem.repository.BookingRepository;

@Repository
public class BookingDao {
	@Autowired
	private BookingRepository bookingRepository;

	//save bookings
	public Booking createBooking(Booking booking) {
		return bookingRepository.save(booking);
	}
	// GET Booking BY ID
	public Optional<Booking> getBookingById(int id) {
		return bookingRepository.findById(id);
	}

	// GET ALL THE Booking RECORDS
	public List<Booking> getAllBooking(){
		return bookingRepository.findAll();	
	}

	// GET Booking BY Flight ID
	public List<Booking> getBookingByFlightId(Integer id) {
		return bookingRepository.getByFlightId(id);
	}

	// GET ALL THE Booking By Date
	public List<Booking> getBookingByDate(LocalDate bookingDate) {
		return bookingRepository.getBookingByDate(bookingDate);
	}
	//get Booking by Status 
	public List<Booking> getBookingByStatus(BookingStatus status) {
		return bookingRepository.findByStatus(status);
	}

	// GET ALL Passenger by Booking id
	public List<Passenger> getPassengerByBooking(int id) {
		return bookingRepository.getPassengerByBookinId(id);
	}

	//GET ALL Payment by Booking id 
	public Optional<Payment> getPaymentByBookingId(int id) {
		return bookingRepository.getPaymentByBookinId(id);
	}

	// DELETE THE Booking
	public void deleteBooking(Booking booking) {
		bookingRepository.delete(booking);
	}

	//CREATING THE PAGE AND SORTING TOGETHER
	public Page<Booking> getBookingByPageAndSorted(int pageNumber , int pageSize , String field){
		Page<Booking> bookings = bookingRepository.findAll(PageRequest.of(pageNumber, pageSize ,Sort.by(field).ascending()));
		if(!bookings.isEmpty()){
			return bookings;
		}
		else {
			throw new RecordNotAvailableException("No records are present");
		}
	}
}
