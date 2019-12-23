package uz.orders.db.dao.interfaces;

import uz.orders.db.entities.Product;

import java.util.List;

public interface ProductDAO {

    List<Product> get();
    Product getById(int id);
    void saveProduct(Product product);
    void deleteById(int id);

}
