package uz.orders.db.dao.interfaces;

import uz.orders.db.entities.Item;

import java.util.List;

public interface ItemDAO {

    List<Item> getAll();
    List<Item> getAllByDocumentId(int id);
    List<Item> getAllByProductId(int id);
    void saveItem(Item item);
    void deleteItem(int id);

}
