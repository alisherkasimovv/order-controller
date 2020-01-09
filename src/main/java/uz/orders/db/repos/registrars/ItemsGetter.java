package uz.orders.db.repos.registrars;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.orders.collections.ItemCollection;

import java.util.List;

public interface ItemsGetter extends JpaRepository<ItemCollection, Integer> {

    @Query("SELECT i.productId, SUM(i.orderQuantity) AS total FROM Item i WHERE i.deleted = FALSE GROUP BY i.productId ORDER BY i.productId")
    List<ItemCollection> sumUpAllOrders();

}
