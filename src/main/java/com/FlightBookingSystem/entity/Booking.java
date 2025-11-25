package com.FlightBookingSystem.entity;
import java.time.LocalDateTime;
import java.util.List;
import org.hibernate.annotations.CreationTimestamp;
import com.FlightBookingSystem.enums.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@CreationTimestamp
	private LocalDateTime bookingDate;
	
	@Enumerated(EnumType.STRING)
	private BookingStatus status;

	@OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
	private List<Passenger> passenger;

	@JoinColumn
	@ManyToOne
	private Flight flight;

	@OneToOne(cascade = CascadeType.ALL)
	private Payment payment;
}
