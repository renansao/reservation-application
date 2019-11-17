package br.com.reservation.court.domain;

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
	
	
}
