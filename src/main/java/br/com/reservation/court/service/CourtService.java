package br.com.reservation.court.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.reservation.court.DAO.CourtDAO;
import br.com.reservation.court.domain.CourtDomain;
import br.com.reservation.court.domain.CourtRequestDomain;

@Service
public class CourtService {
	
	@Autowired
	private CourtDAO courtDAO;
	
	public CourtDomain createCourt(CourtRequestDomain courtRequest) {
		
		CourtDomain court = new CourtDomain();
		court.setCourtName(courtRequest.getCourtName());
		court.setCourtNumber(courtRequest.getCourtNumber());
		
		courtDAO.insert(court);
		
		return court;
	}

	public CourtDomain retrieveCourtByID(String courtID) {
		
		CourtDomain court = new CourtDomain();
		
		court = courtDAO.findByCourtID(courtID);
		
		return court;
	}

	public List<CourtDomain> retrieveAllCourts() {
		
		List<CourtDomain> allCourts = new ArrayList<CourtDomain>();
		
		allCourts = courtDAO.findAll();
		
		return allCourts;
	}
	
}
