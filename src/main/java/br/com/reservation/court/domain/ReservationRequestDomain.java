package br.com.reservation.court.domain;

import java.util.Date;

public class ReservationRequestDomain {
	
	private String courtID;
	
	private Date startDate;
	
	private Date endDate;

	public String getCourtID() {
		return courtID;
	}

	public void setCourtID(String courtID) {
		this.courtID = courtID;
	}

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

}
