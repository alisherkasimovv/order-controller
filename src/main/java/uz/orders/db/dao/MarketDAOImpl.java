package uz.orders.db.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.orders.db.dao.interfaces.MarketDAO;
import uz.orders.db.entities.Market;
import uz.orders.db.repos.MarketRepository;

import java.util.List;

@Service
public class MarketDAOImpl implements MarketDAO {

    private MarketRepository repository;

    @Autowired
    public MarketDAOImpl(MarketRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<Market> get() {
        return repository.findAllByDeletedFalse();
    }

    @Override
    public Market getById(int id) {
        return repository.findById(id);
    }

    @Override
    public void saveMarket(Market market) {
        repository.save(market);
    }

    @Override
    public void deleteById(int id) {
        Market market = repository.getOne(id);
        market.setDeleted(true);
        repository.save(market);
    }
}
