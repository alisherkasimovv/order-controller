package uz.orders.db.dao.registrars;

import org.springframework.stereotype.Service;
import uz.orders.db.dao.interfaces.OrderItemDAO;
import uz.orders.db.entities.items.OrderItem;
import uz.orders.db.repos.registrars.OrderItemRepository;

import java.util.List;

@Service
public class OrderItemDAOImpl implements OrderItemDAO {

    private OrderItemRepository repository;

    public OrderItemDAOImpl(OrderItemRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<OrderItem> getAllOrderItems() {
        return repository.findAllByDeletedFalse();
    }

    @Override
    public List<OrderItem> getAllOrderItemsByDocumentId(int id) {
        return repository.findAllByDocumentId(id);
    }

    @Override
    public void saveOrderItem(OrderItem orderItem) {
        repository.save(orderItem);
    }

    @Override
    public void deleteOrderItem(OrderItem item) {
        item.setDeleted(true);
        repository.save(item);
    }
}
