package uz.orders.db.repos;

import com.sun.tools.javac.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.orders.db.entities.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {

    List<Warehouse> findAllByDeletedFalse();
    Warehouse findById(int id);
    Warehouse findByProductId(int id);

}
