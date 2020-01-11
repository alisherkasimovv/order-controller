package uz.orders.db.dao.interfaces.registrars;

import uz.orders.collections.Filter;
import uz.orders.collections.ItemCollection;
import uz.orders.collections.MarketWithOrders;
import uz.orders.collections.components.OrderWithItems;
import uz.orders.db.entities.registrars.Order;

import java.util.List;

public interface OrderDAO {

    List<OrderWithItems> getAll(Filter filter, boolean filterType);
    List<MarketWithOrders> getAllOrdersForMarket();
    OrderWithItems getByIdWithItems(int id);
    Order getById(int id);
    void saveOrder(OrderWithItems orderWithItems);
    void saveOnlyOrder(Order order);
    void deleteOrder(int id);

}
