package nl.team2.parque_banque_server.service;

import nl.team2.parque_banque_server.model.Sector;
import nl.team2.parque_banque_server.model.repositories.SectorRepository;
import nl.team2.parque_banque_server.utilities.CompanyFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SectorService {

    @Autowired
    private SectorRepository sectorRepo;

    //Lijst met alle sectoren opvragen uit DB
    public Iterable<Sector> sectorIterable(){
        return sectorRepo.findAll();
    }

    //Sector op naam ophalen uit DB
    public Sector sectorOpNaam(String name){
            return sectorRepo.findSectorByName(name);
    }
}
