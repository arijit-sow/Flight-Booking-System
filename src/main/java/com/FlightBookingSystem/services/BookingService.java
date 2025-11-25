package com.FlightBookingSystem.services;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.FlightBookingSystem.dao.BookingDao;
import com.FlightBookingSystem.dto.ResponseStructure;
import com.FlightBookingSystem.entity.Booking;
import com.FlightBookingSystem.entity.Flight;
import com.FlightBookingSystem.entity.Passenger;
import com.FlightBookingSystem.entity.Payment;
import com.FlightBookingSystem.enums.BookingStatus;
import com.FlightBookingSystem.enums.PaymentStatus;
import com.FlightBookingSystem.exception.FlightNotFoundException;
import com.FlightBookingSystem.exception.IdNotFoundException;
import com.FlightBookingSystem.exception.RecordNotAvailableException;
import com.FlightBookingSystem.repository.FlightRepository;

@Service
public class BookingService {

	@Autowired
	private BookingDao bookingDao;
	@Autowired
	private FlightRepository flightRepository;
	PaymentStatus s;

	//save bookings
	public ResponseEntity<ResponseStructure<Booking>> createBooking(Booking booking) {

		//Fetch the Flight from DB
		Optional<Flight> opt = flightRepository.findById(booking.getFlight().getId());
		if (opt.isPresent()) {
			booking.setFlight(opt.get());

			//Check Available Seats and Deduct
			int availableSeat = booking.getFlight().getTotalSeat();
			List<Passenger> pas = booking.getPassenger();
			int total_pas = pas.size();
			availableSeat-=total_pas;
			booking.getFlight().setTotalSeat(availableSeat);

			//Calculate Total Payment Amount
			double famnt = booking.getFlight().getPrice();
			double total_amt = total_pas * famnt;

			//Link Passenger with Booking
			if (total_pas > 0) {
				for (Passenger p : booking.getPassenger()) {
					p.setBooking(booking);
				}
			} else
				throw new RecordNotAvailableException("Passenger details must be passed");

			//Payment Validation
			Payment pay = booking.getPayment();
			PaymentStatus f = s.FAILED;
			if (pay.getMode() == null || pay.getStatus().equals(f))
				throw new RecordNotAvailableException("Payment is failed Or Mode Should be provided");
			else
				booking.getPayment().setAmount(total_amt);

			booking.getPayment().setBooking(booking);

			//Final Response
			ResponseStructure<Booking> response = new ResponseStructure<Booking>();
			response.setStatusCode(HttpStatus.CREATED.value());
			response.setMessage("Booking  Successfull");
			response.setData(bookingDao.createBooking(booking));
			return new ResponseEntity<ResponseStructure<Booking>>(response, HttpStatus.CREATED);
		} else {
			throw new FlightNotFoundException("Flight is not available");
		}
	}

	// GET Booking BY ID
	public ResponseEntity<ResponseStructure<Booking>> getBookingById(int id) {
		ResponseStructure<Booking> response = new ResponseStructure<Booking>();
		Optional<Booking> opt = bookingDao.getBookingById(id);
		if (opt.isPresent()) {
			response.setStatusCode(HttpStatus.FOUND.value());
			response.setMessage("Booking is fetched using id");
			response.setData(opt.get());
			return new ResponseEntity<ResponseStructure<Booking>>(response, HttpStatus.FOUND);
		} else {
			throw new RecordNotAvailableException("Booking Record is not avilable");
		}
	}

	// GET ALL THE Booking RECORDS
	public ResponseEntity<ResponseStructure<List<Booking>>> getAllBooking() {
		ResponseStructure<List<Booking>> response = new ResponseStructure<List<Booking>>();
		List<Booking> booking = bookingDao.getAllBooking();
		if (!booking.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Found All Bookings ");
			response.setData(bookingDao.getAllBooking());
			return new ResponseEntity<ResponseStructure<List<Booking>>>(response, HttpStatus.OK);
		} else {
			throw new RecordNotAvailableException("Bookings is not available");
		}
	}

	// GET Booking BY Flight ID
	public ResponseEntity<ResponseStructure<List<Booking>>> getBookingByFlightId(Integer id) {
		ResponseStructure<List<Booking>> response = new ResponseStructure<List<Booking>>();
		List<Booking> booking = bookingDao.getBookingByFlightId(id);
		if (!booking.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Found All Bookings ");
			response.setData(bookingDao.getAllBooking());
			return new ResponseEntity<ResponseStructure<List<Booking>>>(response, HttpStatus.OK);
		} else {
			throw new RecordNotAvailableException("Bookings is not available");
		}
	}

	// GET ALL THE Booking By Date
	public ResponseEntity<ResponseStructure<List<Booking>>> getBookingByDate(LocalDate bookingDate) {
		ResponseStructure<List<Booking>> response = new ResponseStructure<List<Booking>>();
		List<Booking> booking = bookingDao.getBookingByDate(bookingDate);
		if (!booking.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Found All Bookings ");
			response.setData(bookingDao.getAllBooking());
			return new ResponseEntity<ResponseStructure<List<Booking>>>(response, HttpStatus.OK);
		} else {
			throw new RecordNotAvailableException("Bookings Are not available");
		}
	}

	// GET ALL THE Booking By status
	public ResponseEntity<ResponseStructure<List<Booking>>> getBookingByStatus(BookingStatus status) {
		ResponseStructure<List<Booking>> response = new ResponseStructure<List<Booking>>();
		List<Booking> booking = bookingDao.getBookingByStatus(status);
		if (!booking.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Found All Bookings using Status ");
			response.setData(booking);
			return new ResponseEntity<ResponseStructure<List<Booking>>>(response, HttpStatus.OK);
		} else {
			throw new RecordNotAvailableException("Bookings Are not Available");
		}
	}

	// GET ALL Passenger by Booking id
	public ResponseEntity<ResponseStructure<List<Passenger>>> getPassengerByBooking(int id) {
		ResponseStructure<List<Passenger>> response = new ResponseStructure<List<Passenger>>();
		List<Passenger> booking = bookingDao.getPassengerByBooking(id);
		if (!booking.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Found All Bookings ");
			response.setData(bookingDao.getPassengerByBooking(id));
			return new ResponseEntity<ResponseStructure<List<Passenger>>>(response, HttpStatus.OK);
		} else {
			throw new RecordNotAvailableException("Passenger Details Are not available For Following");
		}
	}

	//GET ALL Payment by Booking id 
	public ResponseEntity<ResponseStructure<Payment>> getPaymentByBookingId(int id) {
		ResponseStructure<Payment> response = new ResponseStructure<Payment>();
		Optional<Payment> opt = bookingDao.getPaymentByBookingId(id);
		if (opt.isPresent()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Found All Bookings ");
			response.setData(opt.get());
			return new ResponseEntity<ResponseStructure<Payment>>(response, HttpStatus.OK);
		} else {
			throw new FlightNotFoundException("Payment deatils is not available");
		}

	}

	//UPDATE THE Booking BY ID
	public ResponseEntity<ResponseStructure<Booking>> updateBooking(int id , BookingStatus status) {
		ResponseStructure<Booking> response = new ResponseStructure<Booking>();

		Optional<Booking> opt = bookingDao.getBookingById(id);
		if (opt.isPresent()) {
			Booking booking = opt.get();
			booking.setStatus(status);
			bookingDao.createBooking(booking);
			response.setStatusCode(HttpStatus.CREATED.value());
			response.setMessage("Passenger Record is Updated of Passenger id " + booking.getId());
			response.setData(booking);
			return new ResponseEntity<ResponseStructure<Booking>>(response, HttpStatus.FOUND);
		} else {
			throw new RecordNotAvailableException("Booking Data is not Available");
		}
	}

	// DELETE THE Booking
	public ResponseEntity<ResponseStructure<String>> deleteBooking(Integer id) {
		ResponseStructure<String> response = new ResponseStructure<String>();
		Optional<Booking> opt = bookingDao.getBookingById(id);
		if (opt.isPresent()) {
			bookingDao.deleteBooking(opt.get());
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Booking Record is Deleted");
			response.setData("Success");
			return new ResponseEntity<ResponseStructure<String>>(response, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Id Not Availlable");
		}
	}

	// SORTING AND PAGING THE RECORDS 
	public ResponseEntity<ResponseStructure<Page<Booking>>> getBookByPageAndSorted(int pageNumber , int pageSize , String field){
		ResponseStructure<Page<Booking>> response = new ResponseStructure<Page<Booking>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("book Record is found in sorted and page form");
		response.setData(bookingDao.getBookingByPageAndSorted(pageNumber, pageSize, field));
		return new ResponseEntity<ResponseStructure<Page<Booking>>>(response, HttpStatus.OK);
	}
}
