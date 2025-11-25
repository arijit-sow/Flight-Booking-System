package com.FlightBookingSystem.repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.FlightBookingSystem.entity.Booking;
import com.FlightBookingSystem.entity.Passenger;
import com.FlightBookingSystem.entity.Payment;
import com.FlightBookingSystem.enums.BookingStatus;

public interface BookingRepository extends JpaRepository<Booking, Integer>{
	
	// GET Booking BY Flight ID
	@Query("select b from Booking b where b.flight.id=:id")
	public List<Booking> getByFlightId(Integer id);
	
	// GET ALL THE Booking By Date
	@Query("select b from Booking b where FUNCTION('date', b.bookingDate)=:date")
	public List<Booking> getBookingByDate(LocalDate date);
	
	// GET ALL THE Booking By status
	public List<Booking> findByStatus(BookingStatus status);
	
	// GET ALL Passenger by Booking id
	@Query("select b.passenger from Booking b where b.id = :id")
	List<Passenger> getPassengerByBookinId(Integer id);
	
	//GET ALL Payment by Booking id 
	@Query("select b.payment from Booking b where b.id = :id")
	Optional<Payment> getPaymentByBookinId(Integer id);
	
	
}
