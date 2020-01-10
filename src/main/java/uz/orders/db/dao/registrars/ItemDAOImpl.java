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
    private ProductDAO productDAO;

    public ItemDAOImpl(ItemRepository repository, WarehouseDAO warehouseDAO, ProductDAO productDAO) {
        this.repository = repository;
        this.warehouseDAO = warehouseDAO;
        this.productDAO = productDAO;
    }

    @Override
    public List<Item> getAllItemsForDocument(int documentId) {
        return repository.findAllByDocumentId(documentId);
    }

    @Override
    public List<ItemCollection> sumUpAllItemQuantities() {
        List<Product> products = productDAO.get();
        List<ItemCollection> collections = new ArrayList<>();

        for (Product product : products) {
            ItemCollection ic = new ItemCollection();
            ic.setProduct(product);
            ic.setTotal(repository.sumUpByProduct(product.getId()));

            collections.add(ic);
        }

        return collections;
    }

    @Override
    public void saveItem(Item item, DocumentType type) {
        if (type == DocumentType.ORDER){
            // ORDER SAVE
            item.setOrder(true);
            item.setIncome(false);
            item.setOutgo(false);

            item.setIncomeAmount(0);
            item.setIncomeCost(0);
            item.setOutgoQuantity(0);
        } else if (type == DocumentType.INCOME) {
            // INCOME SAVE
            item.setOrder(false);
            item.setIncome(true);
            item.setOutgo(false);

            warehouseDAO.saveWarehouse(
                    item.getProductId(),
                    item.getIncomeQuantity()
            );

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
