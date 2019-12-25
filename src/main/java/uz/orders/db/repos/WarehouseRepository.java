package uz.orders.db.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.orders.db.entities.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {

    Warehouse findById(int id);
    Warehouse findByProductId(int id);

}
