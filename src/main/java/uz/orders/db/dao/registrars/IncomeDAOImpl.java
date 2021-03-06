package uz.orders.db.dao.registrars;

import org.springframework.stereotype.Service;
import uz.orders.collections.Filter;
import uz.orders.collections.components.IncomeWithItems;
import uz.orders.configs.ReferenceGenerator;
import uz.orders.db.dao.interfaces.registrars.IncomeDAO;
import uz.orders.db.dao.interfaces.registrars.ItemDAO;
import uz.orders.db.entities.registrars.Income;
import uz.orders.db.entities.registrars.Item;
import uz.orders.db.repos.registrars.IncomeRepository;
import uz.orders.enums.DocumentType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class IncomeDAOImpl implements IncomeDAO {

    private IncomeRepository repository;
    private ItemDAO itemDAO;
    private int reference = 0;

    public IncomeDAOImpl(IncomeRepository repository, ItemDAO itemDAO) {
        this.repository = repository;
        this.itemDAO = itemDAO;
    }

    @Override
    public List<IncomeWithItems> getAll(Filter filter) {
        List<IncomeWithItems> incomeWithItemsList = new ArrayList<>();
        List<Income> incomeList;

        if (filter == null)
            incomeList = repository.findAllByDeletedFalse();
        else
            incomeList = repository.findAllByDeletedFalseAndOrderDateBetween(filter.getStart(), filter.getEnd());

        for (Income income : incomeList) {
            IncomeWithItems incomeWithItems = new IncomeWithItems();
            incomeWithItems.setIncome(income);
            incomeWithItems.setItems(itemDAO.getAllItemsForDocument(income.getId()));

            incomeWithItemsList.add(incomeWithItems);
        }

        return incomeWithItemsList;
    }

    @Override
    public IncomeWithItems getById(int id) {
        IncomeWithItems incomeWithItems = new IncomeWithItems();
        incomeWithItems.setIncome(repository.findById(id));
        incomeWithItems.setItems(itemDAO.getAllItemsForDocument(incomeWithItems.getIncome().getId()));
        return incomeWithItems;
    }

    @Override
    public void saveIncome(IncomeWithItems incomeWithItems) {
        if (incomeWithItems.getIncome().getReference() == null) {
            incomeWithItems.getIncome().setReference(makeReference(LocalDateTime.now()));
        }
        Income income = repository.save(incomeWithItems.getIncome());

        for (Item item : incomeWithItems.getItems()) {
            if (item == null) continue;
            item.setDocumentId(income.getId());
            itemDAO.saveItem(item, DocumentType.INCOME);
        }
    }

    @Override
    public void deleteIncome(int id) {

    }

    private String makeReference(LocalDateTime time) {
        ReferenceGenerator rg = new ReferenceGenerator();

        if (reference == 1000) reference = 0;
        rg.setReference(reference);
        ++reference;

        return rg.generateReferenceNumber(time, "I");
    }

}
