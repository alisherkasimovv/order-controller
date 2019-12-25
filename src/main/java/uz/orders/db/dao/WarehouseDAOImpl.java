package uz.orders.db.dao;

import org.springframework.stereotype.Service;
import uz.orders.collections.WarehouseWithProduct;
import uz.orders.db.dao.interfaces.ProductDAO;
import uz.orders.db.dao.interfaces.WarehouseDAO;
import uz.orders.db.entities.Item;
import uz.orders.db.entities.Product;
import uz.orders.db.entities.Warehouse;
import uz.orders.db.repos.WarehouseRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class WarehouseDAOImpl implements WarehouseDAO {

    private WarehouseRepository repository;
    private ProductDAO productDAO;

    public WarehouseDAOImpl(WarehouseRepository repository, ProductDAO productDAO) {
        this.repository = repository;
        this.productDAO = productDAO;
    }

    @Override
    public List<WarehouseWithProduct> getAll() {
//        List<Warehouse> warehouses = repository.findAll();
//
//        List<WarehouseWithProduct> warehousesWithProduct = new ArrayList<>();
//        for (Warehouse item : warehouses) {
//            WarehouseWithProduct wwp = new WarehouseWithProduct(
//                    item,
//                    productDAO.getById(item.getProductId())
//            );
//
//            warehousesWithProduct.add(wwp);
//        }
//
//        return warehousesWithProduct;

        return null;
    }

    @Override
    public WarehouseWithProduct getById(int id) {
//        Warehouse warehouse = repository.findById(id);
//        Product product = getProduct(warehouse.getProductId());
//
//        return new WarehouseWithProduct(
//                warehouse, product
//        );
        return null;
    }

    @Override
    public WarehouseWithProduct getByProductId(int id) {
//        Warehouse warehouse = repository.findByProductId(id);
//        Product product = getProduct(id);
//
//        return new WarehouseWithProduct(
//                warehouse, product
//        );
        return null;
    }

    @Override
    public void createNewWarehouseItem(Product product) {
//        Warehouse warehouse = new Warehouse();
//        warehouse.setProductId(product.getId());
//        warehouse.setCost(product.getCost());
//        warehouse.setQuantity(0);
//        warehouse.setAmount(recalculateFullAmount(product.getCost(), warehouse.getQuantity()));
//        repository.save(warehouse);
    }

    @Override
    public void saveWarehouse(Product product, Item item) {
//        Warehouse warehouse = repository.findByProductId(product.getId());
//        warehouse.setQuantity(warehouse.getQuantity() + item.getQuantity());
//        warehouse.setAmount(recalculateFullAmount(warehouse.getCost(), warehouse.getQuantity()));
//
//        repository.save(warehouse);
    }

    @Override
    public void deleteWarehouse(int id) {
//        Warehouse warehouse = repository.findByProductId(id);
//        warehouse.setDeleted(true);
//        repository.save(warehouse);
    }

    private Product getProduct(int id) {
        return productDAO.getById(id);
    }

    private double recalculateFullAmount(double cost, double quantity) {
        return cost * quantity;
    }
}
