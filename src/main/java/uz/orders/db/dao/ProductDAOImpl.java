package uz.orders.db.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.orders.db.dao.interfaces.ProductDAO;
import uz.orders.db.entities.Product;
import uz.orders.db.repos.ProductRepository;

import java.util.List;

@Service
public class ProductDAOImpl implements ProductDAO {
    private ProductRepository repository;

    @Autowired
    public ProductDAOImpl(ProductRepository repository) {
        this.repository = repository;
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
    }

    @Override
    public void deleteById(int id) {
        Product product=repository.getOne(id);
        product.setDeleted(true);
        repository.save(product);
    }
}
