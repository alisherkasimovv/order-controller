package uz.orders.db.dao.registrars;

import org.springframework.stereotype.Service;
import uz.orders.collections.components.OrderWithItems;
import uz.orders.db.dao.interfaces.registrars.OrderDAO;
import uz.orders.db.dao.interfaces.registrars.ItemDAO;
import uz.orders.db.entities.registrars.Item;
import uz.orders.db.entities.registrars.Order;
import uz.orders.db.repos.registrars.OrderRepository;
import uz.orders.enums.DocumentType;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDAOImpl implements OrderDAO {

    private OrderRepository repository;
    private ItemDAO itemDAO;

    public OrderDAOImpl(OrderRepository repository, ItemDAO itemDAO) {
        this.repository = repository;
        this.itemDAO = itemDAO;
    }

    @Override
    public List<OrderWithItems> getAll() {
        List<OrderWithItems> orderWithItems = new ArrayList<>();
        List<Order> orders = repository.findAll();

        for (Order order : orders) {
            OrderWithItems owi = new OrderWithItems();
            List<Item> items = itemDAO.getAllItemsForDocument(order.getId());

            owi.setOrder(order);
            owi.setItems(items);

            orderWithItems.add(owi);
        }

        return orderWithItems;
    }

    @Override
    public OrderWithItems getById(int id) {
        Order order = repository.findById(id);

        OrderWithItems orderWithItems = new OrderWithItems();
        orderWithItems.setOrder(order);
        orderWithItems.setItems(itemDAO.getAllItemsForDocument(order.getId()));
        return orderWithItems;
    }

    @Override
    public void saveOrder(OrderWithItems orderWithItems) {
        Order order = repository.save(orderWithItems.getOrder());

        for (Item item : orderWithItems.getItems()) {
            item.setDocumentId(order.getId());
            itemDAO.saveItem(item, DocumentType.ORDER);
        }
    }

    @Override
    public void deleteOrder(int id) {

    }
}
