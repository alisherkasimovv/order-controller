package uz.orders.db.repos.registrars;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.orders.db.entities.registrars.Order;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findAll();
    List<Order> findAllByProvidedFalseOrderByOrderDateAsc();
    List<Order> findAllByOrderDateBetweenOrderByOrderDateAsc(LocalDateTime start, LocalDateTime end);
    List<Order> findAllByOrderDateBetweenAndProvidedFalseOrderByOrderDateAsc(LocalDateTime start, LocalDateTime end);
    List<Order> findAllByMarketIdAndProvidedFalseOrderByOrderDateAsc(int marketId);
    Order findById(int id);
    Order findByReference(String reference);

}
