package uz.orders.db.dao.interfaces;

import uz.orders.collections.Filter;
import uz.orders.collections.IncomeWithItems;

import java.util.List;

public interface IncomeDAO {

    List<IncomeWithItems> getAll();
    List<IncomeWithItems> getFilteredByDates(Filter filter);
    IncomeWithItems getById(int id);
    void saveIncome(IncomeWithItems income);
    void deleteIncome(int id);

}
