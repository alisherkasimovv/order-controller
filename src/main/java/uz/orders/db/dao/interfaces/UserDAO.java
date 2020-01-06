package uz.orders.db.dao.interfaces;

import uz.orders.db.entities.User;

import java.util.List;

public interface UserDAO {

    List<User> getAll();
    User getById(int id);
    User getByUsername(String username);
    List<User> getAllByMarket(int id);
    void saveUser(User user);
    void deleteById(int id);

}
