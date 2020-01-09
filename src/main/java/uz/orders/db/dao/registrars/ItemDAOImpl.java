package uz.orders.db.dao.registrars;

import org.springframework.stereotype.Service;
import uz.orders.collections.ItemCollection;
import uz.orders.db.dao.interfaces.WarehouseDAO;
import uz.orders.db.dao.interfaces.registrars.ItemDAO;
import uz.orders.db.entities.registrars.Item;
import uz.orders.db.repos.registrars.ItemRepository;
import uz.orders.enums.DocumentType;

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
    public List<Item> getAllOrderItems() {
        return repository.findAllByOrderTrue();
    }

    @Override
    public List<Item> getAllIncomeItems() {
        return repository.findAllByIncomeTrue();
    }

    @Override
    public List<Item> getAllOutgoItems() {
        return repository.findAllByOutgoTrue();
    }

    @Override
    public List<ItemCollection> sumUpAllItems() {
        return repository.sumUpAllOrders();
    }

    @Override
    public Item getOneItem(int id) {
        return repository.findById(id);
    }

    @Override
    public void saveItem(Item item, DocumentType type) {
        if (type == DocumentType.ORDER){
            item.setOrder(true);
            item.setIncome(false);
            item.setOutgo(false);
        } else if (type == DocumentType.INCOME) {
            item.setOrder(false);
            item.setIncome(true);
            item.setOutgo(false);

            warehouseDAO.saveWarehouse(
                    item.getProductId(),
                    item.getIncomeQuantity()
            );

        } else if (type == DocumentType.OUTGO) {
            item.setOrder(false);
            item.setIncome(false);
            item.setOutgo(true);

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
