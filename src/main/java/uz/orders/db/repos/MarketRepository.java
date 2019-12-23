package uz.orders.db.repos;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.orders.db.entities.Market;

import java.util.List;

public interface MarketRepository extends JpaRepository<Market,Integer> {

    List<Market> findAllByDeletedFalse();
    Market findById(int id);
    Market findByName(String name);

}
