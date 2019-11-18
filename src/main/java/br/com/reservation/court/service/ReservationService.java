package br.com.reservation.court.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

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
		
		reservation.setStartDate(startDate);
		reservation.setEndDate(endDate);
		
		court.getReservations().add(reservation);
		
		courtDAO.save(court);
		return reservation;
	}
	
}
