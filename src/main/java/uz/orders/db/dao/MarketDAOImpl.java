package uz.orders.db.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.orders.collections.MarketWithUsers;
import uz.orders.db.dao.interfaces.MarketDAO;
import uz.orders.db.dao.interfaces.UserDAO;
import uz.orders.db.entities.Market;
import uz.orders.db.entities.User;
import uz.orders.db.repos.MarketRepository;

import java.util.List;

@Service
public class MarketDAOImpl implements MarketDAO {

    private MarketRepository repository;
    private UserDAO userDAO;

    @Autowired
    public MarketDAOImpl(MarketRepository repository, UserDAO userDAO) {
        this.repository = repository;
        this.userDAO = userDAO;
    }


    @Override
    public List<Market> get() {
        return repository.findAllByDeletedFalse();
    }

    @Override
    public MarketWithUsers getById(int id) {
        Market market = repository.findById(id);
        List<User> users = userDAO.getAllByMarket(market.getId());
        return new MarketWithUsers(market, users);
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
