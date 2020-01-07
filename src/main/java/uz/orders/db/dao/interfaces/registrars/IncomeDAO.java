package uz.orders.db.dao.interfaces.registrars;

import uz.orders.collections.Filter;
import uz.orders.collections.components.IncomeWithItems;

import java.util.List;

public interface IncomeDAO {

    List<IncomeWithItems> getAll(Filter filter);
    IncomeWithItems getById(int id);
    void saveIncome(IncomeWithItems incomeWithItems);
    void deleteIncome(int id);

}
