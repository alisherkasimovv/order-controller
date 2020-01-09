package uz.orders.db.repos.registrars;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.orders.collections.ItemCollection;
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

    /**
     * Searching for item by its id.
     *
     * @param id ID of the item.
     * @return Item.
     */
    Item findById(int id);

}
