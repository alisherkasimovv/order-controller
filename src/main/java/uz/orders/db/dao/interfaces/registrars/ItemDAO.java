package uz.orders.db.dao.interfaces.registrars;

import uz.orders.db.entities.registrars.Item;
import uz.orders.enums.DocumentType;

import java.util.List;

public interface ItemDAO {

    List<Item> getAllItemsForDocument(int documentId);
    List<Item> getAllOrderItems();
    List<Item> getAllIncomeItems();
    List<Item> getAllOutgoItems();
    Item getOneItem(int id);

    /**
     * Saving items by document type.
     * There are three types of documents: Income, Order, Outgo.
     * Type of the document will be represented with enum DocumentType
     *
     * @param item Item
     * @param type DocumentType
     */
    void saveItem(Item item, DocumentType type);
    void deleteItem(List<Item> items);

}