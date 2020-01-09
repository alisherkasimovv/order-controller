package uz.orders.db.repos.registrars;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.orders.db.entities.registrars.Order;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findAll();
    List<Order> findAllByProvidedFalseOrderByOrderDateDesc();
    List<Order> findAllByOrderDateBetweenOrderByOrderDateDesc(LocalDateTime start, LocalDateTime end);
    List<Order> findAllByOrderDateBetweenAndProvidedFalseOrderByOrderDateDesc(LocalDateTime start, LocalDateTime end);
    Order findById(int id);
    Order findByReference(String reference);

}
