package uz.orders.db.repos.registrars;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.orders.db.entities.items.IncomeItem;

import java.util.List;

public interface IncomeItemRepository extends JpaRepository<IncomeItem, Integer> {

    List<IncomeItem> findAllByDeletedFalse();
    List<IncomeItem> findAllByDocumentId(int id);
    List<IncomeItem> findAllByProductId(int id);
    IncomeItem getById(int id);

}
