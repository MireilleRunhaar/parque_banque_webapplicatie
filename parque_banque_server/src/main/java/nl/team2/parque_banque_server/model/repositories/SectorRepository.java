package nl.team2.parque_banque_server.model.repositories;

import nl.team2.parque_banque_server.model.Sector;
import nl.team2.parque_banque_server.utilities.CompanyFormBean;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;
import java.util.List;

@Repository
public interface SectorRepository extends CrudRepository <Sector, Integer> {

    Sector findSectorByName(String name);



}
