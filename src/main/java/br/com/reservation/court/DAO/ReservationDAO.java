package br.com.reservation.court.DAO;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.reservation.court.domain.ReservationDomain;

@Repository
public interface ReservationDAO extends MongoRepository<ReservationDomain, String>{

}
