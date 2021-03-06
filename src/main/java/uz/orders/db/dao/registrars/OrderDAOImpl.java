package uz.orders.db.dao.registrars;

import org.springframework.stereotype.Service;
import uz.orders.collections.Filter;
import uz.orders.collections.MarketWithOrders;
import uz.orders.collections.components.OrderWithItems;
import uz.orders.configs.ReferenceGenerator;
import uz.orders.db.dao.interfaces.MarketDAO;
import uz.orders.db.dao.interfaces.registrars.OrderDAO;
import uz.orders.db.dao.interfaces.registrars.ItemDAO;
import uz.orders.db.entities.Market;
import uz.orders.db.entities.registrars.Item;
import uz.orders.db.entities.registrars.Order;
import uz.orders.db.repos.registrars.OrderRepository;
import uz.orders.enums.DocumentType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDAOImpl implements OrderDAO {

    private OrderRepository repository;
    private ItemDAO itemDAO;
    private MarketDAO marketDAO;
    private int reference = 0;

    public OrderDAOImpl(OrderRepository repository, ItemDAO itemDAO, MarketDAO marketDAO) {
        this.repository = repository;
        this.itemDAO = itemDAO;
        this.marketDAO = marketDAO;
    }

    @Override
    public List<OrderWithItems> getAll(Filter filter, boolean filterType) {
        List<Order> orders;

        if (filter == null) {
            if (filterType) {
                orders = repository.findAllByProvidedFalseOrderByOrderDateAsc();
            } else {
                orders = repository.findAll();
            }
        } else {
            if (filterType) {
                orders = repository.findAllByOrderDateBetweenAndProvidedFalseOrderByOrderDateAsc(filter.getStart(), filter.getEnd());
            } else {
                orders = repository.findAllByOrderDateBetweenOrderByOrderDateAsc(filter.getStart(), filter.getEnd());
            }
        }

        return sortOutCollection(orders);
    }

    @Override
    public List<MarketWithOrders> getAllOrdersForMarket() {
        List<Market> markets = marketDAO.get();
        List<MarketWithOrders> marketWithOrders = new ArrayList<>();

        for (Market market : markets) {
            MarketWithOrders mwo = new MarketWithOrders();
            mwo.setMarket(market);
            mwo.setOrders(this.getForMarket(market.getId()));

            marketWithOrders.add(mwo);
        }
        return marketWithOrders;
    }

    @Override
    public OrderWithItems getByIdWithItems(int id) {
        Order order = repository.findById(id);

        OrderWithItems orderWithItems = new OrderWithItems();
        orderWithItems.setOrder(order);
        orderWithItems.setItems(itemDAO.getAllItemsForDocument(order.getId()));
        return orderWithItems;
    }

    @Override
    public Order getById(int id) {
        return repository.findById(id);
    }

    @Override
    public void saveOrder(OrderWithItems orderWithItems) {
        if (orderWithItems.getOrder().getReference() == null) {
            orderWithItems.getOrder().setReference(makeReference(LocalDateTime.now()));
        }
        Order order = repository.save(orderWithItems.getOrder());

        for (Item item : orderWithItems.getItems()) {
            item.setDocumentId(order.getId());
            itemDAO.saveItem(item, DocumentType.ORDER);
        }
    }

    @Override
    public void saveOnlyOrder(Order order) {
        repository.save(order);
    }

    @Override
    public void deleteOrder(int id) {

    }

    private List<OrderWithItems> sortOutCollection(List<Order> orders) {
        List<OrderWithItems> orderWithItems = new ArrayList<>();

        for (Order order : orders) {
            OrderWithItems owi = new OrderWithItems();
            List<Item> items = itemDAO.getAllItemsForDocument(order.getId());

            owi.setOrder(order);
            owi.setItems(items);

            orderWithItems.add(owi);
        }

        return orderWithItems;
    }

    private List<OrderWithItems> getForMarket(int id) {
        List<Order> orders = repository.findAllByMarketIdAndProvidedFalseOrderByOrderDateAsc(id);
        List<OrderWithItems> orderWithItems = new ArrayList<>();

        for (Order order : orders) {
            OrderWithItems owi = new OrderWithItems();
            owi.setOrder(order);
            owi.setItems(itemDAO.getAllItemsForDocument(order.getId()));
            orderWithItems.add(owi);
        }
        return orderWithItems;
    }

    private String makeReference(LocalDateTime time) {
        ReferenceGenerator rg = new ReferenceGenerator();

        if (reference == 1000) reference = 0;
        rg.setReference(reference);
        ++reference;

        return rg.generateReferenceNumber(time, "O");
    }
}
