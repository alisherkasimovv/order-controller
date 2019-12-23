package uz.orders.db.dao;

import org.springframework.stereotype.Service;
import uz.orders.collections.Filter;
import uz.orders.collections.IncomeWithItems;
import uz.orders.db.dao.interfaces.IncomeDAO;
import uz.orders.db.dao.interfaces.ItemDAO;
import uz.orders.db.entities.Income;
import uz.orders.db.entities.Item;
import uz.orders.db.repos.IncomeRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class IncomeDAOImpl implements IncomeDAO {

    private IncomeRepository repository;
    private ItemDAO itemDAO;

    public IncomeDAOImpl(IncomeRepository repository, ItemDAO itemDAO) {
        this.repository = repository;
        this.itemDAO = itemDAO;
    }

    @Override
    public List<IncomeWithItems> getAll() {
        List<Income> dbIncomes = repository.findAllByDeletedFalse();
        return collectIncomeAndItems(dbIncomes);
    }

    @Override
    public List<IncomeWithItems> getFilteredByDates(Filter filter) {
        List<Income> dbIncomes = repository.findAllByDeletedFalseAndOrderDateBetween(filter.getStart(), filter.getEnd());
        return collectIncomeAndItems(dbIncomes);
    }

    @Override
    public IncomeWithItems getById(int id) {
        Income income = repository.getOne(id);

        IncomeWithItems incomeWithItems = new IncomeWithItems();
        incomeWithItems.setIncome(income);
        incomeWithItems.setItems(itemDAO.getAllByDocumentId(id));

        return incomeWithItems;
    }

    @Override
    public void saveIncome(IncomeWithItems income) {
        repository.save(income.getIncome());

        for (Item item : income.getItems()) {
            item.setDocumentId(income.getIncome().getId());
            itemDAO.saveItem(item);
        }
    }

    @Override
    public void deleteIncome(int id) {
        Income income = repository.getOne(id);
        itemDAO.deleteItem(id);
        income.setDeleted(true);
        repository.save(income);
    }

    private List<IncomeWithItems> collectIncomeAndItems(List<Income> dbIncomes) {
        List<IncomeWithItems> incomes = new ArrayList<>();
        for (Income item : dbIncomes) {
            IncomeWithItems income = new IncomeWithItems();

            income.setIncome(item);
            income.setItems(itemDAO.getAllByDocumentId(item.getId()));

            incomes.add(income);
        }

        return incomes;
    }

}
