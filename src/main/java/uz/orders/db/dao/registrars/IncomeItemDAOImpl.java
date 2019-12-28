package uz.orders.db.dao.registrars;

import org.springframework.stereotype.Service;
import uz.orders.db.dao.interfaces.registrars.IncomeItemDAO;
import uz.orders.db.dao.interfaces.WarehouseDAO;
import uz.orders.db.entities.items.IncomeItem;
import uz.orders.db.repos.registrars.IncomeItemRepository;

import java.util.List;

@Service
public class IncomeItemDAOImpl implements IncomeItemDAO {

    private IncomeItemRepository repository;
    private WarehouseDAO warehouseDAO;

    public IncomeItemDAOImpl(IncomeItemRepository repository, WarehouseDAO warehouseDAO) {
        this.repository = repository;
        this.warehouseDAO = warehouseDAO;
    }

    @Override
    public List<IncomeItem> getAllIncomeItemsByDocumentId(int id) {
        return repository.findAllByDocumentId(id);
    }

    @Override
    public void saveIncomeItems(List<IncomeItem> incomeItems) {
        for (IncomeItem incomeItem : incomeItems) {
            warehouseDAO.saveWarehouse(
                    incomeItem.getProductId(),
                    incomeItem.getCost(),
                    incomeItem.getQuantity()
            );
        }
        repository.saveAll(incomeItems);
    }

    @Override
    public void deleteIncomeItems(List<IncomeItem> incomeItems) {
        for (IncomeItem incomeItem : incomeItems) {
            warehouseDAO.subtractFromWarehouse(incomeItem.getProductId(), incomeItem.getQuantity());
            incomeItem.setDeleted(true);
        }

        repository.saveAll(incomeItems);
    }
}
