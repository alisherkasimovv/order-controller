package uz.orders.db.dao;

import org.springframework.stereotype.Service;
import uz.orders.collections.ItemCollection;
import uz.orders.collections.WarehouseWithProduct;
import uz.orders.db.dao.interfaces.ProductDAO;
import uz.orders.db.dao.interfaces.WarehouseDAO;
import uz.orders.db.entities.Product;
import uz.orders.db.entities.Warehouse;
import uz.orders.db.repos.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductDAOImpl implements ProductDAO {

    private ProductRepository repository;
    private WarehouseDAO warehouseDAO;

    public ProductDAOImpl(ProductRepository repository, WarehouseDAO warehouseDAO) {
        this.repository = repository;
        this.warehouseDAO = warehouseDAO;
    }

    @Override
    public List<WarehouseWithProduct> get() {
        List<WarehouseWithProduct> wwp = new ArrayList<>();
        List<Product> products = repository.findAllByDeletedFalse();

        for (Product product : products) {
            WarehouseWithProduct wpr = new WarehouseWithProduct();
            wpr.setProduct(product);
            wpr.setWarehouse(warehouseDAO.getByProductId(product.getId()));

            wwp.add(wpr);
        }

        return wwp;
    }

    @Override
    public List<ItemCollection> sumUpAllOrderQuantities() {
        List<Product> products = repository.findAllByDeletedFalse();
        List<ItemCollection> collections = new ArrayList<>();

        for (Product product : products) {
            ItemCollection ic = new ItemCollection();
            ic.setProduct(product);
            Warehouse warehouse = warehouseDAO.getByProductId(product.getId());
            ic.setTotal(warehouse.getQuantityOrdered());

            collections.add(ic);
        }

        return collections;
    }

    @Override
    public Product getById(int id) {
        return repository.findById(id);
    }

    @Override
    public void saveProduct(Product product) {
        Product temp = this.getById(product.getId());
        if (temp != null) {
            temp.setName(product.getName());
            repository.save(temp);
        } else {
            Product pr = repository.save(product);
            warehouseDAO.createWarehouse(pr.getId());
        }
    }

    @Override
    public void deleteById(int id) {
        Product product = repository.getOne(id);
        product.setDeleted(true);
        repository.save(product);
    }
}
