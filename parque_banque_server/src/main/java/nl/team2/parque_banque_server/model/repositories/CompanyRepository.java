package nl.team2.parque_banque_server.model.repositories;

import nl.team2.parque_banque_server.model.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Id> {

}
