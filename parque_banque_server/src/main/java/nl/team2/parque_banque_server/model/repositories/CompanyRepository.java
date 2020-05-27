package nl.team2.parque_banque_server.model.repositories;

import nl.team2.parque_banque_server.model.Company;
import nl.team2.parque_banque_server.model.Sector;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;
import java.util.List;

@Repository
public interface CompanyRepository extends CrudRepository<Company, String> {


}
