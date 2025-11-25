package com.FlightBookingSystem.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.FlightBookingSystem.dto.ResponseStructure;
import com.FlightBookingSystem.entity.Payment;
import com.FlightBookingSystem.enums.Mode;
import com.FlightBookingSystem.enums.PaymentStatus;
import com.FlightBookingSystem.services.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	@Autowired
	private PaymentService paymentService;

	//Record a Payment record
	@PostMapping
	public ResponseEntity<ResponseStructure<Payment>> addPassenger(@RequestBody Payment payment){
		return paymentService.recordPayment(payment);
	}

	// GET Payment BY ID
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Payment>> getPassengerByid(@PathVariable Integer id){
		return  paymentService.getPaymentById(id);
	}

	// GET ALL THE Payment RECORDS
	@GetMapping("/all")
	public ResponseEntity<ResponseStructure<List<Payment>>> getAllPayment(){
		return paymentService.getAllPayment();
	}

	// UPDATE THE Payment BY ID
	@PutMapping("/{id}/{status}")
	public ResponseEntity<ResponseStructure<Payment>> updatePayment(@PathVariable Integer id , @PathVariable PaymentStatus status){
		return paymentService.updatePayment(id , status);
	}

	// GET Payment By Status
	@GetMapping("/status/{status}")
	public ResponseEntity<ResponseStructure<List<Payment>>> getPaymentByStatus(@PathVariable PaymentStatus status){
		return paymentService.getPaymentByStatus(status);
	}

	// GET Payment Record By mode 
	@GetMapping("/mode/{mode}")
	public ResponseEntity<ResponseStructure<List<Payment>>> getPaymentByMode(@PathVariable Mode mode){
		return paymentService.getPaymentByMode(mode);
	}

	// SORTING AND PAGING
	@GetMapping("/sortedpage/{pageNumber}/{pageSize}/{field}")
	public ResponseEntity<ResponseStructure<Page<Payment>>> getBookByPagination(@PathVariable Integer pageNumber ,@PathVariable Integer  pageSize , @PathVariable String field){
		return paymentService.getPaymentByPageAndSorted(pageNumber, pageSize, field);
	}


}
