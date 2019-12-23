package uz.orders.db.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.orders.db.entities.Income;

import java.time.LocalDateTime;
import java.util.List;

public interface IncomeRepository extends JpaRepository<Income, Integer> {

    List<Income> findAllByDeletedFalse();
    List<Income> findAllByDeletedFalseAndOrderDateBetween(LocalDateTime start, LocalDateTime end);
    Income findByReference(String reference);

}
