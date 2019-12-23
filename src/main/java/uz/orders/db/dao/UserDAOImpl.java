package uz.orders.db.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.orders.db.dao.interfaces.UserDAO;
import uz.orders.db.entities.User;
import uz.orders.db.repos.UserRepository;

import java.util.List;

@Service
public class UserDAOImpl implements UserDAO {
    private UserRepository repository;

    @Autowired
    public UserDAOImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<User> get() {
        return repository.findAllByDeletedFalse();
    }

    @Override
    public User getById(int id) {
        return repository.findById(id);
    }

    @Override
    public User getbyUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public void saveUser(User user) {
        repository.save(user);
    }

    @Override
    public void deleteById(int id) {
        User user= repository.getOne(id);
        user.setDeleted(true);
        repository.save(user);
    }
}
