package uz.orders.db.dao.interfaces.registrars;

import uz.orders.db.entities.items.IncomeItem;

import java.util.List;

public interface IncomeItemDAO {

    List<IncomeItem> getAllIncomeItemsByDocumentId(int id);
    void saveIncomeItems(List<IncomeItem> incomeItems);
    void deleteIncomeItems(List<IncomeItem> incomeItems);

}
