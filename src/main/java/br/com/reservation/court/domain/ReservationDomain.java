package br.com.reservation.court.domain;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ReservationCollection")
public class ReservationDomain {

	private PlayerDomain player;

	private LocalDateTime startDate;

	private LocalDateTime endDate;
	
	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public PlayerDomain getPlayer() {
		return player;
	}

	public void setPlayer(PlayerDomain player) {
		this.player = player;
	}
	
}
