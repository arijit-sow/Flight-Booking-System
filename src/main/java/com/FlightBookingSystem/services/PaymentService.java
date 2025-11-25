package com.FlightBookingSystem.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.FlightBookingSystem.dao.PaymentDao;
import com.FlightBookingSystem.dto.ResponseStructure;
import com.FlightBookingSystem.entity.Payment;
import com.FlightBookingSystem.enums.Mode;
import com.FlightBookingSystem.enums.PaymentStatus;
import com.FlightBookingSystem.exception.RecordNotAvailableException;

@Service
public class PaymentService {
	@Autowired
	private PaymentDao paymentDao;

	//Add a Payment record
	public ResponseEntity<ResponseStructure<Payment>> recordPayment(Payment payment) {
		ResponseStructure<Payment> response = new ResponseStructure<Payment>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Flight Information is Successfull added");
		response.setData(paymentDao.recordPayment(payment));
		return new ResponseEntity<ResponseStructure<Payment>>(response, HttpStatus.CREATED);
	}

	// GET Payment BY ID
	public ResponseEntity<ResponseStructure<Payment>> getPaymentById(int id) {
		ResponseStructure<Payment> response = new ResponseStructure<Payment>();
		Optional<Payment> opt = paymentDao.getPaymentById(id);
		if (opt.isPresent()) {
			response.setStatusCode(HttpStatus.FOUND.value());
			response.setMessage("Payment record  is fetched using id");
			response.setData(opt.get());
			return new ResponseEntity<ResponseStructure<Payment>>(response, HttpStatus.FOUND);
		} else {
			throw new RecordNotAvailableException("Payment Records are not avilable");
		}
	}

	// GET ALL THE Payment RECORDS
	public ResponseEntity<ResponseStructure<List<Payment>>> getAllPayment() {
		ResponseStructure<List<Payment>> response = new ResponseStructure<List<Payment>>();
		List<Payment> payment = paymentDao.getAllPayment();
		if (!payment.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Found All Payment records");
			response.setData(payment);
			return new ResponseEntity<ResponseStructure<List<Payment>>>(response, HttpStatus.OK);
		} else {
			throw new RecordNotAvailableException("Payment is not available");
		}
	}

	// UPDATE THE Payment BY ID
	public ResponseEntity<ResponseStructure<Payment>> updatePayment(int id , PaymentStatus status) {
		ResponseStructure<Payment> response = new ResponseStructure<Payment>();

		Optional<Payment> opt = paymentDao.getPaymentById(id);
		if (opt.isPresent()) {
			Payment payment = opt.get();
			payment.setStatus(status);
			paymentDao.recordPayment(payment);
			response.setStatusCode(HttpStatus.CREATED.value());
			response.setMessage("Payment Record is Updated of Passenger id ");
			response.setData(payment);
			return new ResponseEntity<ResponseStructure<Payment>>(response, HttpStatus.FOUND);
		} else {
			throw new RecordNotAvailableException("Payment Data is not Available");
		}
	}

	// GET Payment Record By Status
	public ResponseEntity<ResponseStructure<List<Payment>>> getPaymentByStatus(PaymentStatus status) {
		ResponseStructure<List<Payment>> response = new ResponseStructure<List<Payment>>();
		List<Payment> payment = paymentDao.getPaymentByStatus(status);
		if (!payment.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Found All Payment records by status");
			response.setData(payment);
			return new ResponseEntity<ResponseStructure<List<Payment>>>(response, HttpStatus.OK);
		} else {
			throw new RecordNotAvailableException("Payment is not available");
		}
	}

	// GET Payment Record By mode 
	public ResponseEntity<ResponseStructure<List<Payment>>> getPaymentByMode(Mode mode) {
		ResponseStructure<List<Payment>> response = new ResponseStructure<List<Payment>>();
		List<Payment> payment = paymentDao.getPaymentByMode(mode);
		if (!payment.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Found All Payment records by Mode");
			response.setData(payment);
			return new ResponseEntity<ResponseStructure<List<Payment>>>(response, HttpStatus.OK);
		} else {
			throw new RecordNotAvailableException("Payment is not available");
		}
	}

	// SORTING AND PAGING THE RECORDS
	public ResponseEntity<ResponseStructure<Page<Payment>>> getPaymentByPageAndSorted(int pageNumber, int pageSize,
			String field) {
		ResponseStructure<Page<Payment>> response = new ResponseStructure<Page<Payment>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Payment Record is found in sorted and page form");
		response.setData(paymentDao.getPaymentByPageAndSorted(pageNumber, pageSize, field));
		return new ResponseEntity<ResponseStructure<Page<Payment>>>(response, HttpStatus.OK);
	}

}
