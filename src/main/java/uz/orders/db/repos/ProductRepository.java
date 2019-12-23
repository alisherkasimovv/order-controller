package uz.orders.db.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.orders.db.entities.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findAllByDeletedFalse();
    Product findById(int id);

}
