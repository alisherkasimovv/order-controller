package uz.orders.db.dao.registrars;

import org.springframework.stereotype.Service;
import uz.orders.collections.ItemCollection;
import uz.orders.db.dao.interfaces.ProductDAO;
import uz.orders.db.dao.interfaces.WarehouseDAO;
import uz.orders.db.dao.interfaces.registrars.ItemDAO;
import uz.orders.db.entities.Product;
import uz.orders.db.entities.registrars.Item;
import uz.orders.db.repos.registrars.ItemRepository;
import uz.orders.enums.DocumentType;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemDAOImpl implements ItemDAO {

    private ItemRepository repository;
    private WarehouseDAO warehouseDAO;

    public ItemDAOImpl(ItemRepository repository, WarehouseDAO warehouseDAO) {
        this.repository = repository;
        this.warehouseDAO = warehouseDAO;
    }

    @Override
    public List<Item> getAllItemsForDocument(int documentId) {
        return repository.findAllByDocumentId(documentId);
    }

    @Override
    public void saveItem(Item item, DocumentType type) {
        if (type == DocumentType.ORDER){
            warehouseDAO.makeOrderToWarehouse(item.getProductId(), item.getOrderQuantity());
            // ORDER SAVE
            item.setOrder(true);
            item.setIncome(false);
            item.setOutgo(false);

            item.setIncomeAmount(0);
            item.setIncomeCost(0);
            item.setOutgoQuantity(0);
        } else if (type == DocumentType.INCOME) {
            warehouseDAO.makeIncomeToWarehouse(item.getProductId(), item.getIncomeQuantity());
            // INCOME SAVE
            item.setOrder(false);
            item.setIncome(true);
            item.setOutgo(false);

        } else if (type == DocumentType.OUTGO) {
            // OUTGO SAVE
            item.setOrder(false);
            item.setIncome(false);
            item.setOutgo(true);

            item.setOrderQuantity(0);

            warehouseDAO.subtractFromWarehouse(
                    item.getProductId(),
                    item.getOutgoQuantity()
            );

        }
        repository.save(item);
    }

    @Override
    public void deleteItem(List<Item> items) {
        repository.deleteAll(items);
    }

}
