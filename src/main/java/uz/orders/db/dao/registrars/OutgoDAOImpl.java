package uz.orders.db.dao.registrars;

import org.springframework.stereotype.Service;
import uz.orders.collections.components.OrderWithItems;
import uz.orders.collections.components.OutgoWithItems;
import uz.orders.configs.ReferenceGenerator;
import uz.orders.db.dao.interfaces.registrars.ItemDAO;
import uz.orders.db.dao.interfaces.registrars.OrderDAO;
import uz.orders.db.dao.interfaces.registrars.OutgoDAO;
import uz.orders.db.entities.registrars.Item;
import uz.orders.db.entities.registrars.Order;
import uz.orders.db.entities.registrars.Outgo;
import uz.orders.db.repos.registrars.OutgoRepository;
import uz.orders.enums.DocumentType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OutgoDAOImpl implements OutgoDAO {

    private OutgoRepository repository;
    private OrderDAO orderDAO;
    private ItemDAO itemDAO;
    private int reference = 0;

    public OutgoDAOImpl(OutgoRepository repository, OrderDAO orderDAO, ItemDAO itemDAO) {
        this.repository = repository;
        this.orderDAO = orderDAO;
        this.itemDAO = itemDAO;
    }

    @Override
    public List<OutgoWithItems> getAll() {
        List<OutgoWithItems> outgoWithItemsList = new ArrayList<>();
        List<Outgo> outgoList = repository.findAllByDeletedFalse();

        for (Outgo outgo : outgoList) {
            OutgoWithItems oui = new OutgoWithItems();
            oui.setOutgo(outgo);
            oui.setItems(itemDAO.getAllItemsForDocument(outgo.getId()));

            outgoWithItemsList.add(oui);
        }

        return outgoWithItemsList;
    }

    @Override
    public OutgoWithItems getById(int id) {
        OutgoWithItems oui = new OutgoWithItems();

        oui.setOutgo(repository.findById(id));
        oui.setItems(itemDAO.getAllItemsForDocument(id));

        return oui;
    }

    @Override
    public void saveOutgo(OrderWithItems orderWithItems) {
        Outgo outgo = new Outgo();
        outgo.setReference(makeReference(LocalDateTime.now()));
        outgo.setLinkedDocumentId(orderWithItems.getOrder().getId());
        outgo.setMarketId(orderWithItems.getOrder().getMarketId());
        Outgo savedOutgo = repository.save(outgo);

        int id = orderWithItems.getOrder().getId();
        Order order = orderDAO.getById(id);
        order.setProvided(orderWithItems.getOrder().isProvided());
        orderDAO.saveOnlyOrder(order);

        for (Item item : orderWithItems.getItems()) {
            item.setDocumentId(order.getId());
            itemDAO.saveItem(item, DocumentType.ORDER);

            item.setDocumentId(savedOutgo.getId());
            itemDAO.saveItem(item, DocumentType.OUTGO);
        }
    }

    @Override
    public void deleteOutgo(int id) {
    }

    private String makeReference(LocalDateTime time) {
        ReferenceGenerator rg = new ReferenceGenerator();

        if (reference == 1000) reference = 0;
        rg.setReference(reference);
        ++reference;

        return rg.generateReferenceNumber(time, "G");
    }
}
