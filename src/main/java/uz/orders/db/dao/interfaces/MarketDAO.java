package uz.orders.db.dao.interfaces;

import uz.orders.db.entities.Market;

import java.util.List;

public interface MarketDAO {

    List<Market> get();
    Market getById(int id);
    void saveMarket(Market market);
    void deleteById(int id);

}
