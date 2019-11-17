package br.com.reservation.court.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.reservation.court.domain.CourtDomain;
import br.com.reservation.court.domain.CourtRequestDomain;
import br.com.reservation.court.service.CourtService;

@RestController
public class CourtController {
	
	@Autowired
	private CourtService courtService;
	
	@PostMapping(value="/createCourt")
	public ResponseEntity<?> createCourt (@RequestBody CourtRequestDomain courtRequest){
		
		CourtDomain court = new CourtDomain();
		
		try {
			court = courtService.createCourt(courtRequest);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(court, HttpStatus.OK);
		
	}
}
