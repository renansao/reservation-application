package br.com.reservation.court.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.reservation.court.DAO.PlayerDAO;
import br.com.reservation.court.domain.PlayerDomain;
import br.com.reservation.court.domain.PlayerRequestDomain;

@Service
public class PlayerService {
	
	@Autowired
	private PlayerDAO playerDAO;

	public PlayerDomain registerPlayer(PlayerRequestDomain playerRequest) {
		
		PlayerDomain player = new PlayerDomain();
		player.setAge(playerRequest.getAge());
		player.setName(playerRequest.getName());
		
		playerDAO.insert(player);
		
		return player;
	}

}
