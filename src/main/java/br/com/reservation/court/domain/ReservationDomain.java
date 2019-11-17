package br.com.reservation.court.domain;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ReservationCollection")
public class ReservationDomain {

	private PlayerDomain player;

	private Date startDate;

	private Date endDate;

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public PlayerDomain getPlayer() {
		return player;
	}

	public void setPlayer(PlayerDomain player) {
		this.player = player;
	}
	
}
