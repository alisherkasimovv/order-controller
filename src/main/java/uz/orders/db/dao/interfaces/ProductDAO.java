package uz.orders.db.dao.interfaces;

import uz.orders.collections.ItemCollection;
import uz.orders.collections.WarehouseWithProduct;
import uz.orders.db.entities.Product;

import java.util.List;

public interface ProductDAO {

    List<WarehouseWithProduct> get();
    List<ItemCollection> sumUpAllOrderQuantities();
    Product getById(int id);
    void saveProduct(Product product);
    void deleteById(int id);

}
