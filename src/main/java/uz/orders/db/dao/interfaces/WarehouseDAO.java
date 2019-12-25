package uz.orders.db.dao.interfaces;

import uz.orders.collections.WarehouseWithProduct;
import uz.orders.db.entities.Item;
import uz.orders.db.entities.Product;

import java.util.List;

public interface WarehouseDAO {

    List<WarehouseWithProduct> getAll();
    WarehouseWithProduct getById(int id);
    WarehouseWithProduct getByProductId(int id);
    void createNewWarehouseItem(Product product);
    void saveWarehouse(Product product, Item item);
    void deleteWarehouse(int id);

}
