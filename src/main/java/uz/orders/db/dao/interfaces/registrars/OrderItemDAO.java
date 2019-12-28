package uz.orders.db.dao.interfaces.registrars;

import uz.orders.db.entities.items.OrderItem;

import java.util.List;

public interface OrderItemDAO {

    List<OrderItem> getAllOrderItems();
    List<OrderItem> getAllOrderItemsByDocumentId(int id);
    void saveOrderItem(OrderItem orderItem);
    void deleteOrderItem(int id);

}
