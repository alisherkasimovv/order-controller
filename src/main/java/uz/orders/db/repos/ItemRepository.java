package uz.orders.db.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.orders.db.entities.Item;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {

    List<Item> findAllByDeletedFalse();
    List<Item> findAllByDocumentId(int id);
    List<Item> findAllByProductId(int id);

}
