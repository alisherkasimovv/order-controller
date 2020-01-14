package uz.orders.db.dao.interfaces;

import uz.orders.db.entities.Warehouse;

import java.util.List;

public interface WarehouseDAO {

    List<Warehouse> getAll();
    Warehouse getById(int id);
    Warehouse getByProductId(int id);
    String createWarehouse(int productId);
    String makeOrderToWarehouse(int productId, double orderQuantity);
    String makeIncomeToWarehouse(int productId, double incomingQuantity);
    String saveWarehouse(int productId, double quantity);
    String subtractFromWarehouse(int productId, double quantity);
    void deleteWarehouse(int id);

}
