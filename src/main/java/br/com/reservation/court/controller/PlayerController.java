package br.com.reservation.court.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.reservation.court.domain.PlayerDomain;
import br.com.reservation.court.domain.PlayerRequestDomain;
import br.com.reservation.court.service.PlayerService;

@RestController
public class PlayerController {
	
	@Autowired
	private PlayerService playerService;
	
	@PostMapping(value="/registerPlayer")
	public ResponseEntity<?> registerPlayer(@Valid @RequestBody PlayerRequestDomain playerRequest) {
		
		PlayerDomain player = new PlayerDomain();
		
		try {
			player = playerService.registerPlayer(playerRequest);
		}catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(player, HttpStatus.OK);

	}
	
}
