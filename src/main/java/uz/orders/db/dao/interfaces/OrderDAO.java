package uz.orders.db.dao.interfaces;

import uz.orders.collections.components.OrderWithItems;

import java.util.List;

public interface OrderDAO {

    List<OrderWithItems> getAll();
    OrderWithItems getById(int id);
    void saveOrder(OrderWithItems orderWithItems);
    void deleteOrder(int id);

}
