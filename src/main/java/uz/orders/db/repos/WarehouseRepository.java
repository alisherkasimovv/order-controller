package uz.orders.db.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.orders.db.entities.Warehouse;

import java.util.List;

public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {

    List<Warehouse> findAllByDeletedFalse();
    Warehouse findById(int id);
    Warehouse findByProductId(int id);

}
