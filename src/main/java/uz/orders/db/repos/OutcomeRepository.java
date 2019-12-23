package uz.orders.db.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.orders.db.entities.Outcome;

import java.time.LocalDateTime;
import java.util.List;

public interface OutcomeRepository extends JpaRepository<Outcome, Integer> {

    List<Outcome> findAllByDeletedFalse();
    List<Outcome> findAllByDeletedFalseAndOrderDateBetween(LocalDateTime start, LocalDateTime end);
    Outcome findByReference(String reference);

}
