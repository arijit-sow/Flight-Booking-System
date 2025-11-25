# ✈️ Flight Booking System – Spring Boot

> A simple yet functional backend application that enables users to search flights, book tickets, manage passengers, and process payments — built using Spring Boot and PostgreSQL with clean layered architecture.

A complete backend application built using Spring Boot to manage flights, passenger bookings, and payment processing. It provides RESTful APIs for real-time flight seat availability, booking status updates, and secure payment handling. The project demonstrates layered architecture and relational database management using Spring Data JPA.

---

## 🚀 Features

- CRUD operations for Flights, Bookings, Passengers, and Payments
- Dynamic seat availability check during booking
- Payment integration with mode & status tracking
- Relational entity mapping using JPA + Hibernate
- Exception handling and validation for safe API usage
- PostgreSQL database support
- APIs tested using Postman

---

## 🛠️ Tech Stack

| Category | Technology |
|---------|------------|
| Language | Java |
| Framework | Spring Boot |
| Architecture | MVC (Controller–Service–Repository) |
| ORM | Spring Data JPA + Hibernate |
| Database | PostgreSQL |
| Tools | Postman, Maven, Git/GitHub |

---

## 🗄️ ER Diagram (Relationship Overview)

---

## 📌 API Endpoints (Sample)

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/flights` | Get all flights |
| POST | `/flights` | Add a new flight |
| GET | `/flights/{id}` | Get flight by ID |
| POST | `/bookings` | Create a flight booking |
| GET | `/bookings/{id}` | Fetch booking by ID |
| GET | `/bookings/date?bookingDate=yyyy-MM-dd` | Find bookings by date |
| POST | `/payments` | Make a payment for a booking |
| GET | `/payments/{id}` | Get payment details |

> More endpoints can be added depending on features implemented.

---

## 🧩 Database Schema

### Flight Table
- id (PK)
- airline  
- source  
- destination  
- departureTime  
- arrivalTime  
- totalSeat  
- price  

### Booking Table
- id (PK)  
- bookingDate  
- status (CONFIRMED / WAITING / FAILED)  
- flight_id (FK)  

### Passenger Table
- id (PK)  
- name, age, gender, seatNumber, contactNumber  
- booking_id (FK)  

### Payment Table
- id (PK)  
- mode (UPI / CARD / NETBANKING etc.)  
- status (CONFIRMED / FAILED)  
- booking_id (FK)  
