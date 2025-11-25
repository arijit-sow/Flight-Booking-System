package com.FlightBookingSystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.FlightBookingSystem.dto.ResponseStructure;
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(FlightNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleFNFE(FlightNotFoundException exception) {
		ResponseStructure<String> response = new ResponseStructure<String>();
		response.setStatusCode(HttpStatus.NOT_FOUND.value());
		response.setMessage("Failure");
		response.setData(exception.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(response , HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleIDNF(IdNotFoundException exception) {
		ResponseStructure<String> response = new ResponseStructure<String>();
		response.setStatusCode(HttpStatus.NOT_FOUND.value());
		response.setMessage("Failure");
		response.setData(exception.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(response , HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(RecordNotAvailableException.class)
	public ResponseEntity<ResponseStructure<String>> handleRNAE(RecordNotAvailableException exception) {
		ResponseStructure<String> response = new ResponseStructure<String>();
		response.setStatusCode(HttpStatus.NOT_FOUND.value());
		response.setMessage("Failure");
		response.setData(exception.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(response , HttpStatus.NOT_FOUND);
	}

}