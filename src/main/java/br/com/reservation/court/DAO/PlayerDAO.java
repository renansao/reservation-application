package br.com.reservation.court.DAO;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.reservation.court.domain.PlayerDomain;

@Repository
public interface PlayerDAO extends MongoRepository<PlayerDomain, String>{
	
	PlayerDomain findByPlayerID(String playerID);

}
