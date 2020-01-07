package uz.orders.db.dao.interfaces.registrars;

import uz.orders.collections.Filter;
import uz.orders.collections.components.OrderWithItems;

import java.util.List;

public interface OrderDAO {

    List<OrderWithItems> getAll(Filter filter);
    OrderWithItems getById(int id);
    void saveOrder(OrderWithItems orderWithItems);
    void deleteOrder(int id);

}
