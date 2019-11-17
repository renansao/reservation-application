package br.com.reservation.court.DAO;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.reservation.court.domain.CourtDomain;

@Repository
public interface CourtDAO extends MongoRepository<CourtDomain, String>{
	
	CourtDomain findByCourtNumber(int courtNumber);
	
}
