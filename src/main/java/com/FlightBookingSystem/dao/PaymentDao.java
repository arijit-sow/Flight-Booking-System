package com.FlightBookingSystem.dao;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import com.FlightBookingSystem.entity.Payment;
import com.FlightBookingSystem.enums.Mode;
import com.FlightBookingSystem.enums.PaymentStatus;
import com.FlightBookingSystem.exception.RecordNotAvailableException;
import com.FlightBookingSystem.repository.PaymentRepository;

@Repository
public class PaymentDao {
	@Autowired
	private PaymentRepository paymentRepository;

	//Add a Payment record
	public Payment recordPayment(Payment payment) {
		return paymentRepository.save(payment);
	}

	// GET Payment BY ID
	public Optional<Payment> getPaymentById(int id) {
		return paymentRepository.findById(id);
	}

	// GET ALL THE Payment RECORDS
	public List<Payment> getAllPayment(){
		return paymentRepository.findAll();
	}

	// UPDATE THE Payment BY ID
	public Payment updatePayment(Payment payment) {
		return paymentRepository.save(payment);
	}

	// GET Payment Record By Status
	public List<Payment> getPaymentByStatus(PaymentStatus status) {
		return paymentRepository.findByStatus(status);
	}

	// GET Payment Record By mode 
	public List<Payment> getPaymentByMode(Mode mode) {
		return paymentRepository.findByMode(mode);
	}

	//CREATING THE PAGE AND SORTING TOGETHER
	public Page<Payment> getPaymentByPageAndSorted(int pageNumber , int pageSize , String field){
		Page<Payment> payments = paymentRepository.findAll(PageRequest.of(pageNumber, pageSize ,Sort.by(field).ascending()));
		if(!payments.isEmpty()){
			return payments;
		}
		else {
			throw new RecordNotAvailableException("No records are present");
		}
	}

}
