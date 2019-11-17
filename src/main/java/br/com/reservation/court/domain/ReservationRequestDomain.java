package br.com.reservation.court.domain;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class ReservationRequestDomain {
	
	@NotNull
	private String courtID;
	
	@NotNull
	private String playerID;
	
	@NotNull
	private Date startDate;
	
	@NotNull
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

	public String getCourtID() {
		return courtID;
	}

	public void setCourtID(String courtID) {
		this.courtID = courtID;
	}

	public String getPlayerID() {
		return playerID;
	}

	public void setPlayerID(String playerID) {
		this.playerID = playerID;
	}
	
}
