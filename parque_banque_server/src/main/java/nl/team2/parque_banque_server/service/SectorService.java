package nl.team2.parque_banque_server.service;

import nl.team2.parque_banque_server.model.Sector;
import nl.team2.parque_banque_server.model.repositories.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SectorService {

    @Autowired
    private SectorRepository sectorRepo;

    //Lijst met alle sectoren
    public Iterable<Sector> sectorIterable(){
        return sectorRepo.findAll();
    }
}
