package br.com.reservation.court.domain;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "CourtCollection")
public class CourtDomain {
	
	@Id
	private String courtID;
	
	@NotNull
	private String courtName;
	
	@NotNull
	private int courtNumber;
	
	private String imageUrl;
	
	private String sport;
	
	private List<ReservationDomain> reservations = new ArrayList<ReservationDomain>();

	public String getCourtID() {
		return courtID;
	}

	public String getCourtName() {
		return courtName;
	}

	public void setCourtName(String courtName) {
		this.courtName = courtName;
	}

	public int getCourtNumber() {
		return courtNumber;
	}

	public void setCourtNumber(int courtNumber) {
		this.courtNumber = courtNumber;
	}

	public List<ReservationDomain> getReservations() {
		return reservations;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getSport() {
		return sport;
	}

	public void setSport(String sport) {
		this.sport = sport;
	}
	
}