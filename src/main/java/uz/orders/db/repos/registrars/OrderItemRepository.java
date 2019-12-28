package uz.orders.db.repos.registrars;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.orders.db.entities.items.OrderItem;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

    List<OrderItem> findAllByDeletedFalse();
    List<OrderItem> findAllByDocumentId(int id);
    List<OrderItem> findAllByProductId(int id);
    OrderItem getById(int id);

}
