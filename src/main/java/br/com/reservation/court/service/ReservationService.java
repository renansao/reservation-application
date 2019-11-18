package br.com.reservation.court.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

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
		
		if ((startDate.isBefore(now) || endDate.isBefore(now)) ||
				(startDate.isAfter(endDate))) {
			throw new Exception("Data ou horário inválidos");
		}
		
		reservation.setStartDate(startDate);
		reservation.setEndDate(endDate);
		
		court.getReservations().add(reservation);
		
		courtDAO.save(court);
		return reservation;
	}
	
}
