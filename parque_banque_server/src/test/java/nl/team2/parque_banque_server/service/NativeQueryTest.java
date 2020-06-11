//package nl.team2.parque_banque_server.service;
//
//import nl.team2.parque_banque_server.model.Sector;
//import nl.team2.parque_banque_server.model.statistics.HoofdPartRepository;
//import nl.team2.parque_banque_server.model.statistics.HoofdPartResult;
//import nl.team2.parque_banque_server.model.statistics.HoofdPartService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//@SpringBootTest
//public class NativeQueryTest {
//
//    @Autowired
//    private SectorService sectorService;
//
//    @Test
//    void dbTest() {
//        List<Sector> results = sectorService.returnList();
//        System.out.println(results.get(0));
//    }
//
////    @NamedNativeQuery(
////            name = "hi",
////            query = "SELECT * FROM user"
////    )
////
////    @Autowired
////    private EntityManager em;
////
////    @Test
////    void dbTest() {
////        Query query = em.createNativeQuery("SELECT * FROM user");
////    }
//
////    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb://localhost:3306/parque-banque.odb");
////    private final EntityManager entityManager = emf.createEntityManager();
////
////    @Test
////    void dbTest() {
////        Query query = entityManager.createNativeQuery("SELECT * FROM User WHERE id=4001");
////        List<Object> results = query.getResultList();
////        for (Object object : results) {
////            System.out.println(object);
////        }
////    }
//}
