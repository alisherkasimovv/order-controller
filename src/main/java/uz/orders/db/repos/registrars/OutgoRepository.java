package uz.orders.db.repos.registrars;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.orders.db.entities.registrars.Outgo;

import java.time.LocalDateTime;
import java.util.List;

public interface OutgoRepository extends JpaRepository<Outgo, Integer> {

    List<Outgo> findAllByDeletedFalse();
    List<Outgo> findAllByDeletedFalseAndOrderDateBetween(LocalDateTime start, LocalDateTime end);
    Outgo findById(int id);
    Outgo findByReference(String reference);

}
