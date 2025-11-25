package com.FlightBookingSystem.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import com.FlightBookingSystem.enums.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@CreationTimestamp
	private LocalDateTime paymentDate;
	private Double amount;
	@Enumerated(EnumType.STRING)
	private Mode mode;
	@Enumerated(EnumType.STRING)
	private PaymentStatus status;
	@JsonIgnore
	@OneToOne //join Column
	private Booking booking;
}
