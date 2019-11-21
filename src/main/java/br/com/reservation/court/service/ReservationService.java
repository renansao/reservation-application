package br.com.reservation.court.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.reservation.court.DAO.CourtDAO;
import br.com.reservation.court.DAO.PlayerDAO;
import br.com.reservation.court.domain.CourtDomain;
import br.com.reservation.court.domain.PlayerDomain;
import br.com.reservation.court.domain.ReservationDomain;
import br.com.reservation.court.domain.ReservationRequestDomain;
import br.com.reservation.court.util.DateFormatter;

@Service
public class ReservationService {
	
	@Autowired
	private PlayerDAO playerDAO;
	
	@Autowired
	private CourtDAO courtDAO;
	
	@Autowired
	private DateFormatter df;
	
	private int MIN_RESERVATION_PERIOD_IN_MINUTES = 60;
	private int MAX_RESERVATION_PERIOD_IN_MINUTES = 120;
	private int MAX_RESERVATION_WINDOW_IN_DAYS = 21;

	public ReservationDomain makeReservation(ReservationRequestDomain reservationRequest) throws Exception {
		
		PlayerDomain player = new PlayerDomain();
		player = playerDAO.findByPlayerID(reservationRequest.getPlayerID());
		
		CourtDomain court = new CourtDomain();
		court = courtDAO.findByCourtID(reservationRequest.getCourtID());
		
		ReservationDomain reservation = new ReservationDomain();
		reservation.setPlayer(player);
		
		LocalDateTime now = LocalDateTime.now(ZoneId.of("Chile/Continental"));
		
		LocalDateTime startDate = LocalDateTime.parse(reservationRequest.getStartDate(), df.formatter);
		LocalDateTime endDate = LocalDateTime.parse(reservationRequest.getEndDate(), df.formatter);
		
		//Businness Rules (each comment line match the lines in the if clause)
		//=========================================================================
		//Both dates must be after the actual moment
		//The end date cant be before the start date
		//The reservation can't be made after 3 weeks from the actual moment 
		//The reservation period must not be under 60 minutes
		//The reservation period must not be over 120 mintues
		
		LocalDateTime maxReservationDateWindow = LocalDateTime.now(ZoneId.of("Chile/Continental"));
		maxReservationDateWindow = maxReservationDateWindow.plusDays(MAX_RESERVATION_WINDOW_IN_DAYS);
		
		 System.out.println(ChronoUnit.MINUTES.between(startDate, endDate));
		
		if ((startDate.isBefore(now) || endDate.isBefore(now)) ||
				(startDate.isAfter(endDate))||
				((startDate.isAfter(maxReservationDateWindow) || endDate.isAfter(maxReservationDateWindow))) ||
				(ChronoUnit.MINUTES.between(startDate, endDate)) < MIN_RESERVATION_PERIOD_IN_MINUTES || 
				(ChronoUnit.MINUTES.between(startDate, endDate)) > MAX_RESERVATION_PERIOD_IN_MINUTES) {
			throw new Exception("Invalid date or time");
		}
		
		//Check to see if the reservation datetime is already being used (each comment line match the lines in the if clause)
		//============================================================================================================================
		//The requested datetime for the reservation start date cant be between the start and end date of an existing reservation
		//The requested datetime for the reservation end date cant be between the start and end date of an existing reservation
		//The requested datetime for the reservation starttime cant be before the start time of a reservation that already exists,
		//and the endDate cant be after the end date of the same reservation, (otherwise the requested reservation would overlap the
		//one that already exists)
		//The requested datetime for the reservation startdate cant be the same as the startdate of an existing reservation
		//The requested datetime for the reservation enddate cant be the same as the enddate of an existing reservation
		
		for (ReservationDomain reservationInCourt : courtDAO.findByCourtID(reservationRequest.getCourtID()).getReservations()) {
			if (startDate.isAfter(reservationInCourt.getStartDate()) && startDate.isBefore(reservationInCourt.getEndDate()) ||
					endDate.isAfter(reservationInCourt.getStartDate()) && endDate.isBefore(reservationInCourt.getEndDate()) ||
					startDate.isBefore(reservationInCourt.getStartDate()) && endDate.isAfter(reservationInCourt.getEndDate()) ||
					startDate.isEqual(reservationInCourt.getStartDate()) ||
					endDate.isEqual(reservationInCourt.getEndDate())){
				throw new Exception("Reservation datetime is already being used");
			}
		}
		
		reservation.setStartDate(startDate);
		reservation.setEndDate(endDate);
		
		court.getReservations().add(reservation);
		
		courtDAO.save(court);
		return reservation;
	}

	public List<ReservationDomain> retrieveReservations(String courtID, String date) throws Exception {
		
		if (date.length() != 8) {
			throw new Exception("Invalid Date");
		}
		
		int yearInt = Integer.parseInt((String) date.subSequence(0, 4));
		int monthInt = Integer.parseInt((String) date.subSequence(4, 6));
		int dayInt = Integer.parseInt((String) date.subSequence(6, 8));
		
		LocalDate requestedDate = LocalDate.of(yearInt, monthInt, dayInt);
		
		List<ReservationDomain> reservationsInRequestedDate = new ArrayList<ReservationDomain>();
		
		for (ReservationDomain reservationsInCourt : courtDAO.findByCourtID(courtID).getReservations()) {
			if (reservationsInCourt.getStartDate().toLocalDate().isEqual(requestedDate)) {
				reservationsInRequestedDate.add(reservationsInCourt);
			}
		}
		
		return reservationsInRequestedDate;
	}
	
}
