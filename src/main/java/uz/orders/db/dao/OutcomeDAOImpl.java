package uz.orders.db.dao;

import org.springframework.stereotype.Service;
import uz.orders.collections.Filter;
import uz.orders.collections.OutcomeWithItems;
import uz.orders.db.dao.interfaces.OutcomeDAO;
import uz.orders.db.dao.interfaces.ItemDAO;
import uz.orders.db.entities.Outcome;
import uz.orders.db.entities.Item;
import uz.orders.db.repos.OutcomeRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class OutcomeDAOImpl implements OutcomeDAO {

    private OutcomeRepository repository;
    private ItemDAO itemDAO;

    public OutcomeDAOImpl(OutcomeRepository repository, ItemDAO itemDAO) {
        this.repository = repository;
        this.itemDAO = itemDAO;
    }

    @Override
    public List<OutcomeWithItems> getAll() {
        List<Outcome> dbOutcomes = repository.findAllByDeletedFalse();
        return collectOutcomeAndItems(dbOutcomes);
    }

    @Override
    public List<OutcomeWithItems> getFilteredByDates(Filter filter) {
        List<Outcome> dbOutcomes = repository.findAllByDeletedFalseAndOrderDateBetween(filter.getStart(), filter.getEnd());
        return collectOutcomeAndItems(dbOutcomes);
    }

    @Override
    public OutcomeWithItems getById(int id) {
        Outcome outcome = repository.getOne(id);

        OutcomeWithItems outcomeWithItems = new OutcomeWithItems();
        outcomeWithItems.setOutcome(outcome);
        outcomeWithItems.setItems(itemDAO.getAllByDocumentId(id));

        return outcomeWithItems;
    }

    // TODO There is no option for saving id of the product on backend.
    @Override
    public void saveOutcome(OutcomeWithItems outcome) {
        repository.save(outcome.getOutcome());

        for (Item item : outcome.getItems()) {
            item.setDocumentId(outcome.getOutcome().getId());
            itemDAO.saveItem(item);
        }
    }

    @Override
    public void deleteOutcome(int id) {
        Outcome outcome = repository.getOne(id);
        itemDAO.deleteItem(id);
        outcome.setDeleted(true);
        repository.save(outcome);
    }

    private List<OutcomeWithItems> collectOutcomeAndItems(List<Outcome> dbOutcomes) {
        List<OutcomeWithItems> outcomes = new ArrayList<>();
        for (Outcome item : dbOutcomes) {
            OutcomeWithItems outcome = new OutcomeWithItems();

            outcome.setOutcome(item);
            outcome.setItems(itemDAO.getAllByDocumentId(item.getId()));

            outcomes.add(outcome);
        }

        return outcomes;
    }

}