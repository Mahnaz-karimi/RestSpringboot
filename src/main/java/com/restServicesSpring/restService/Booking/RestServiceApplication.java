package com.restServicesSpring.restService.Booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import javax.persistence.Id;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.persistence.*;
import java.util.Collection;



@SpringBootApplication
public class RestServiceApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(RestServiceApplication.class, args);

	}

	@Autowired
	BookingRepository bookingRepository;


	@Override
	public void run(String... args) throws Exception {
		// repository
		for(BookingEntity b : this.bookingRepository.findAll())
			System.out.println("Booking name is: " + b.getBooking_name());
	}
}

@Repository
interface BookingRepository extends JpaRepository<BookingEntity, Long> {

}

@RestController
class BookingController {

	@Autowired BookingRepository bookingRepository;

	@RequestMapping("/booking")
	Collection<BookingEntity> bookings(){
		return this.bookingRepository.findAll();
	}
}

@Entity
@Table(name = "booking")
class BookingEntity {
	@Id
	@GeneratedValue
	private int id;
	private String booking_name;

	public BookingEntity(String booking_name) {
		this.booking_name = booking_name;
	}

	public BookingEntity() {

	}
	public int getId() {
		return id;
	}

	public String getBooking_name() {
		return booking_name;
	}
}