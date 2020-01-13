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

    @Override
    public List<Warehouse> getAll() {
        return repository.findAllByDeletedFalse();
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
    public String createWarehouse(int productId) {
        Warehouse warehouse = repository.findByProductId(productId);
        if (warehouse == null) {
            warehouse = new Warehouse();
            warehouse.setProductId(productId);
            warehouse.setQuantity(0);
        }

        repository.save(warehouse);

        return "Created lot in warehouse for chosen product and it's quantity has been set to 0";
    }

    @Override
    public String saveWarehouse(int productId, double quantity) {
        Warehouse warehouse = repository.findByProductId(productId);
        warehouse.setQuantity(warehouse.getQuantity() + quantity);

        repository.save(warehouse);

        return "" + warehouse.getQuantity();
    }

    @Override
    public String subtractFromWarehouse(int productId, double quantity) {
        Warehouse warehouse = repository.findByProductId(productId);
        if (warehouse.getQuantity() >= quantity) {
            warehouse.setQuantity(warehouse.getQuantity() - quantity);

            repository.save(warehouse);
        }

        return "Cannot subtract from warehouse." +
                " Overall quantity: " + warehouse.getQuantity() +
                " Quantity need to be subtracted: " + quantity;
    }

    @Override
    public void deleteWarehouse(int id) {
        repository.deleteById(id);
    }
}
