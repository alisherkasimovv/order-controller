package uz.orders.db.dao;

import org.springframework.stereotype.Service;
import uz.orders.db.dao.interfaces.ItemDAO;
import uz.orders.db.entities.Item;
import uz.orders.db.repos.ItemRepository;

import java.util.List;

@Service
public class ItemDAOImpl implements ItemDAO {

    private ItemRepository repository;

    public ItemDAOImpl(ItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Item> getAll() {
        return repository.findAllByDeletedFalse();
    }

    @Override
    public List<Item> getAllByDocumentId(int id) {
        return repository.findAllByDocumentId(id);
    }

    @Override
    public List<Item> getAllByProductId(int id) {
        return repository.findAllByProductId(id);
    }

    @Override
    public void saveItem(Item item) {
        repository.save(item);
    }

    @Override
    public void deleteItem(int id) {
        List<Item> items = repository.findAllByDocumentId(id);

        for (Item item : items) {
            item.setDeleted(true);
            repository.save(item);
        }
    }
}
