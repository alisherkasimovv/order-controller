package uz.orders.db.dao;

import org.springframework.stereotype.Service;
import uz.orders.db.dao.interfaces.WarehouseDAO;
import uz.orders.db.entities.Warehouse;
import uz.orders.db.repos.WarehouseRepository;

import java.util.List;

@Service
public class WarehouseDAOImpl implements WarehouseDAO {

    private WarehouseRepository repository;
    private String responseText;

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
            warehouse.setProductId(productId);
            warehouse.setQuantityOrdered(0);
            warehouse.setQuantityIncome(0);
            warehouse.setQuantityInStock(0);
        }

        repository.save(warehouse);

        return "Created lot in warehouse for chosen product and it's quantity has been set to 0";
    }

    /**
     * Method will be called when Order is performed.
     * During this action warehouse object will get ordered quantity. Incoming quantity will be set to zero,
     * because this field is used to store all income quantities before new orders performed.
     *
     * @param productId The ID of the particular product.
     * @param orderQuantity Ordered quantity.
     * @return Response text with results.
     */
    @Override
    public String makeOrderToWarehouse(int productId, double orderQuantity) {
        Warehouse warehouse = this.getByProductId(productId);
        if (warehouse != null) {
            double count = warehouse.getQuantityOrdered();
            
            warehouse.setQuantityOrdered(count + orderQuantity);
            warehouse.setQuantityIncome(0);

            responseText = "Order quantity of chosen product: " + warehouse.getQuantityOrdered();
            repository.save(warehouse);
        }

        return responseText;
    }

    /**
     * Making income for particular product.
     * Before saving income quantity will be distracted from order quantity and added to quantity in stock.
     *
     * @param productId The ID of the particular product.
     * @param incomingQuantity Incoming quantity.
     * @return Response text with results.
     */
    @Override
    public String makeIncomeToWarehouse(int productId, double incomingQuantity) {
        Warehouse warehouse = this.getByProductId(productId);
        if (warehouse != null) {
            warehouse.setQuantityIncome(incomingQuantity);

            double quantityOrdered = warehouse.getQuantityOrdered();
            if (quantityOrdered < incomingQuantity) {
                warehouse.setQuantityOrdered(0);
            } else {
                warehouse.setQuantityOrdered(quantityOrdered - incomingQuantity);
            }
            warehouse.setQuantityInStock(warehouse.getQuantityInStock() + incomingQuantity);

            responseText = "Income has been saved: " + incomingQuantity
                    + "<br>Quantity of that product now: " + warehouse.getQuantityOrdered();
            repository.save(warehouse);
        }

        return responseText;
    }

    @Override
    public String saveWarehouse(int productId, double quantity) {
        Warehouse warehouse = repository.findByProductId(productId);
        warehouse.setQuantityInStock(warehouse.getQuantityInStock() + quantity);

        repository.save(warehouse);

        return "" + warehouse.getQuantityInStock();
    }

    @Override
    public String subtractFromWarehouse(int productId, double quantity) {
        Warehouse warehouse = this.getByProductId(productId);
        if (warehouse.getQuantityInStock() >= quantity) {
            warehouse.setQuantityInStock(warehouse.getQuantityInStock() - quantity);

            responseText = quantity + " has been subtracted from warehouse.";

            repository.save(warehouse);
        } else {
            responseText = "Cannot subtract from warehouse." +
                    "<br>Overall quantity: " + warehouse.getQuantityInStock() +
                    "<br>Quantity need to be subtracted by outgo: " + quantity;
        }

        return responseText;
    }

    @Override
    public void deleteWarehouse(int id) {
        repository.deleteById(id);
    }
}
