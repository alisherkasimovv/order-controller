package uz.orders.db.dao.interfaces;

import uz.orders.collections.MarketWithUsers;
import uz.orders.db.entities.Market;

import java.util.List;

public interface MarketDAO {

    List<Market> get();
    MarketWithUsers getById(int id);
    void saveMarket(Market market);
    void deleteById(int id);

}
