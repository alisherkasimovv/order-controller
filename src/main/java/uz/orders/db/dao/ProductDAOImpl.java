package uz.orders.db.dao;

import org.springframework.stereotype.Service;
import uz.orders.db.dao.interfaces.ProductDAO;
import uz.orders.db.dao.interfaces.WarehouseDAO;
import uz.orders.db.entities.Product;
import uz.orders.db.repos.ProductRepository;

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
    public List<Product> get() {
        return repository.findAllByDeletedFalse();
    }

    @Override
    public Product getById(int id) {
        return repository.findById(id);
    }

    @Override
    public void saveProduct(Product product) {
        repository.save(product);
        Product pr = repository.findByName(product.getName());
        warehouseDAO.createWarehouse(pr.getId());
    }

    @Override
    public void deleteById(int id) {
        Product product = repository.getOne(id);
        product.setDeleted(true);
        repository.save(product);
    }
}
