package uz.orders.db.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.orders.db.entities.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {

    List<User> findAllByDeletedFalse();
    User findById(int id);
    User findByUsername(String username);

}
