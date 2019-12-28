package uz.orders.db.dao.registrars;

import org.springframework.stereotype.Service;
import uz.orders.collections.components.OrderWithItems;
import uz.orders.db.dao.interfaces.registrars.OrderDAO;
import uz.orders.db.dao.interfaces.registrars.OrderItemDAO;
import uz.orders.db.entities.items.OrderItem;
import uz.orders.db.entities.registrars.Order;
import uz.orders.db.repos.registrars.OrderRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDAOImpl implements OrderDAO {

    private OrderRepository repository;
    private OrderItemDAO orderItemDAO;

    public OrderDAOImpl(OrderRepository repository, OrderItemDAO orderItemDAO) {
        this.repository = repository;
        this.orderItemDAO = orderItemDAO;
    }

    @Override
    public List<OrderWithItems> getAll() {
        List<OrderWithItems> orderWithItems = new ArrayList<>();
        List<Order> orders = repository.findAll();

        for (Order order : orders) {
            OrderWithItems owi = new OrderWithItems();
            List<OrderItem> items = orderItemDAO.getAllOrderItemsByDocumentId(order.getId());

            owi.setOrder(order);
            owi.setItems(items);

            orderWithItems.add(owi);
        }

        return orderWithItems;
    }

    @Override
    public OrderWithItems getById(int id) {
        Order order = repository.findById(id);

        return new OrderWithItems(
                order,
                orderItemDAO.getAllOrderItemsByDocumentId(order.getId())
        );
    }

    @Override
    public void saveOrder(OrderWithItems orderWithItems) {
        Order order = repository.save(orderWithItems.getOrder());

        for (OrderItem item : orderWithItems.getItems()) {
            item.setDocumentId(order.getId());
            orderItemDAO.saveOrderItem(item);
        }
    }

    @Override
    public void deleteOrder(int id) {

    }
}
