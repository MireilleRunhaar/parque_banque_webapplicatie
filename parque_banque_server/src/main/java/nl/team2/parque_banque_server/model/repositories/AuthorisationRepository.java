package nl.team2.parque_banque_server.model.repositories;

import nl.team2.parque_banque_server.model.Authorisation;
import org.hibernate.dialect.function.AbstractAnsiTrimEmulationFunction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorisationRepository extends CrudRepository<Authorisation, Integer> {

    List<Authorisation> findAllByUserName(String userName);

    Authorisation findByIban(String iban);




}
