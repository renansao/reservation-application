package br.com.reservation.court.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.reservation.court.domain.ReservationDomain;
import br.com.reservation.court.domain.ReservationRequestDomain;
import br.com.reservation.court.service.ReservationService;

@RestController
public class ReservationController {
	
	@Autowired
	private ReservationService reservationService;
	
	@PostMapping(value="/makeReservation")
	public ResponseEntity<?> makeReservation (@RequestBody ReservationRequestDomain reservationRequest){
		
		ReservationDomain reservation = new ReservationDomain();
		
		try {
			reservation = reservationService.makeReservation(reservationRequest);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(reservation, HttpStatus.OK);
		
	}
	
	@GetMapping(value="/retrieveReservations/{courtID}/{date}")
	public ResponseEntity<?> retrieveReservations (@PathVariable String courtID, @PathVariable String date){
		List<ReservationDomain> reservationsOnCourtInTheDay = new ArrayList<ReservationDomain>();
		
		try {
			reservationsOnCourtInTheDay = reservationService.retrieveReservations(courtID, date);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(reservationsOnCourtInTheDay, HttpStatus.OK);
	}
}
