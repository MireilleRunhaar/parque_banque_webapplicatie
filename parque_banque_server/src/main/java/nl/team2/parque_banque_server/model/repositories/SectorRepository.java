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

    @Query(value = "select *,  avg(p.balance_cent) as gemiddelde_saldo\n" +
            "from sector s \n" +
            "join company c on s.id=c.sector_id\n" +
            "join payment_account p on c.kvk_nr=p.company_kvk_nr\n" +
            "where p.dtype='BusinessAccount'\n" +
            "group by s.name",nativeQuery = true)
    List<Sector>getAverageBalanceSector();

}
