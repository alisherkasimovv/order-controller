package uz.orders.db.dao.interfaces.registrars;

import uz.orders.collections.Filter;
import uz.orders.collections.ItemCollection;
import uz.orders.collections.components.OrderWithItems;

import java.util.List;

public interface OrderDAO {

    List<OrderWithItems> getAll(Filter filter, boolean filterType);
    List<OrderWithItems> getAllOrdersForMarket(int marketId);
    OrderWithItems getById(int id);
    void saveOrder(OrderWithItems orderWithItems);
    void deleteOrder(int id);

}
