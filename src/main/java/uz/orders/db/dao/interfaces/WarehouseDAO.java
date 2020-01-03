package uz.orders.db.dao.interfaces;

import uz.orders.db.entities.Warehouse;

import java.util.List;

public interface WarehouseDAO {

    List<Warehouse> getAll();
    Warehouse getById(int id);
    Warehouse getByProductId(int id);
    void createWarehouse(int productId);
    void saveWarehouse(int productId, double quantity);
    void subtractFromWarehouse(int productId, double quantity);
    void deleteWarehouse(int id);

}
