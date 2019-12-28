package uz.orders.db.dao.registrars;

import org.springframework.stereotype.Service;
import uz.orders.collections.components.IncomeWithItems;
import uz.orders.db.dao.interfaces.registrars.IncomeDAO;
import uz.orders.db.dao.interfaces.registrars.IncomeItemDAO;
import uz.orders.db.entities.items.IncomeItem;
import uz.orders.db.entities.registrars.Income;
import uz.orders.db.repos.registrars.IncomeRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class IncomeDAOImpl implements IncomeDAO {

    private IncomeRepository repository;
    private IncomeItemDAO incomeItemDAO;

    public IncomeDAOImpl(IncomeRepository repository, IncomeItemDAO incomeItemDAO) {
        this.repository = repository;
        this.incomeItemDAO = incomeItemDAO;
    }

    @Override
    public List<IncomeWithItems> getAll() {
        List<IncomeWithItems> incomeWithItemsList = new ArrayList<>();
        List<Income> incomeList = repository.findAllByDeletedFalse();

        for (Income income : incomeList) {
            IncomeWithItems incomeWithItems = new IncomeWithItems();
            incomeWithItems.setIncome(income);
            incomeWithItems.setItems(incomeItemDAO.getAllIncomeItemsByDocumentId(income.getId()));

            incomeWithItemsList.add(incomeWithItems);
        }

        return incomeWithItemsList;
    }

    @Override
    public IncomeWithItems getById(int id) {
        IncomeWithItems incomeWithItems = new IncomeWithItems();
        incomeWithItems.setIncome(repository.findById(id));
        incomeWithItems.setItems(incomeItemDAO.getAllIncomeItemsByDocumentId(incomeWithItems.getIncome().getId()));
        return incomeWithItems;
    }

    @Override
    public void saveIncome(IncomeWithItems incomeWithItems) {
        repository.save(incomeWithItems.getIncome());

        for (IncomeItem incomeItem : incomeWithItems.getItems()) {
            incomeItem.setDocumentId(incomeWithItems.getIncome().getId());
        }

        incomeItemDAO.saveIncomeItems(incomeWithItems.getItems());
    }

    @Override
    public void deleteIncome(int id) {

    }

}
