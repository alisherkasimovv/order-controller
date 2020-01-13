package uz.orders.db.repos.registrars;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.orders.db.entities.registrars.Item;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {

    /**
     * Searching by document ID.
     * Returns list of items for particular document.
     *
     * @param documentId ID of chosen document.
     * @return java.util.List Items.
     */
    List<Item> findAllByDocumentId(int documentId);

    /**
     * Searching for all outgo items.
     *
     * @return java.util.List Items.
     */
    List<Item> findAllByOutgoTrue();

    /**
     * Searching for all income items.
     *
     * @return java.util.List Items.
     */
    List<Item> findAllByIncomeTrue();

    /**
     * Searching for all order items.
     *
     * @return java.util.List Items.
     */
    List<Item> findAllByOrderTrue();

    @Query("SELECT SUM(i.orderQuantity) FROM Item i WHERE i.productId = ?1 AND i.orderQuantity > 0 AND i.deleted = FALSE AND i.provided = FALSE")
    Long sumUpByProduct(int productId);

    /**
     * Searching for item by its id.
     *
     * @param id ID of the item.
     * @return Item.
     */
    Item findById(int id);

}
