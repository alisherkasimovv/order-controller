package uz.orders.db.dao;

import org.springframework.stereotype.Service;
import uz.orders.db.dao.interfaces.WarehouseDAO;
import uz.orders.db.entities.Warehouse;
import uz.orders.db.repos.WarehouseRepository;

import java.util.List;

@Service
public class WarehouseDAOImpl implements WarehouseDAO {

    private WarehouseRepository repository;

    public WarehouseDAOImpl(WarehouseRepository repository) {
        this.repository = repository;
    }

    private double recalculateFullAmount(double cost, double quantity) {
        return cost * quantity;
    }

    @Override
    public List<Warehouse> getAll() {
        return repository.findAll();
    }

    @Override
    public Warehouse getById(int id) {
        return repository.findById(id);
    }

    @Override
    public Warehouse getByProductId(int id) {
        return repository.findByProductId(id);
    }

    @Override
    public void createWarehouse(int productId) {
        Warehouse warehouse = repository.findByProductId(productId);
        if (warehouse == null) {
            warehouse = new Warehouse();
            warehouse.setProductId(productId);
            warehouse.setQuantity(0);
        }

        repository.save(warehouse);
    }

    @Override
    public void saveWarehouse(int productId, double quantity) {
        Warehouse warehouse = repository.findByProductId(productId);
        warehouse.setQuantity(warehouse.getQuantity() + quantity);

        repository.save(warehouse);
    }

    @Override
    public void subtractFromWarehouse(int productId, double quantity) {
        Warehouse warehouse = repository.findByProductId(productId);
        if (warehouse.getQuantity() < quantity) {
            warehouse.setQuantity(warehouse.getQuantity() - quantity);

            repository.save(warehouse);
        }
    }

    @Override
    public void deleteWarehouse(int id) {
        Warehouse warehouse = repository.findById(id);
        warehouse.setDeleted(true);
        repository.save(warehouse);
    }
}
