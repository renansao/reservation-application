package br.com.reservation.court.domain;

import javax.validation.constraints.NotNull;

public class ReservationRequestDomain {
	
	@NotNull
	private String courtID;
	
	@NotNull
	private String playerID;
	
	@NotNull
	private String startDate;
	
	@NotNull
	private String endDate;
	
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
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
