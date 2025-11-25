package com.FlightBookingSystem.entity;

import com.FlightBookingSystem.enums.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Passenger {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Integer age;
	@Enumerated(EnumType.STRING)
	private Gender gender;
	private Integer seatNumber;
	private Long contactNumber;
	
	@JsonIgnore
	@JoinColumn
	@ManyToOne
	private Booking booking;
}
