package uz.orders.db.dao.registrars;

import org.springframework.stereotype.Service;
import uz.orders.collections.components.OutgoWithItems;
import uz.orders.db.dao.interfaces.registrars.ItemDAO;
import uz.orders.db.dao.interfaces.registrars.OutgoDAO;
import uz.orders.db.entities.registrars.Item;
import uz.orders.db.entities.registrars.Outgo;
import uz.orders.db.repos.registrars.OutgoRepository;
import uz.orders.enums.DocumentType;

import java.util.ArrayList;
import java.util.List;

@Service
public class OutgoDAOImpl implements OutgoDAO {

    private OutgoRepository repository;
    private ItemDAO itemDAO;

    public OutgoDAOImpl(OutgoRepository repository, ItemDAO itemDAO) {
        this.repository = repository;
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
    public void saveOutgo(OutgoWithItems outgoWithItems) {
        Outgo outgo = repository.save(outgoWithItems.getOutgo());

        for (Item item : outgoWithItems.getItems()) {
            item.setDocumentId(outgo.getId());
            itemDAO.saveItem(item, DocumentType.OUTGO);
        }
    }

    @Override
    public void deleteOutgo(int id) {

    }
}
