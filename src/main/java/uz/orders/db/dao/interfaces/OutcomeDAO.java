package uz.orders.db.dao.interfaces;

import uz.orders.collections.Filter;
import uz.orders.collections.OutcomeWithItems;

import java.util.List;

public interface OutcomeDAO {

    List<OutcomeWithItems> getAll();
    List<OutcomeWithItems> getFilteredByDates(Filter filter);
    OutcomeWithItems getById(int id);
    void saveOutcome(OutcomeWithItems outcome);
    void deleteOutcome(int id);

}
