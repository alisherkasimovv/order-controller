package uz.orders.db.dao.interfaces.registrars;

import uz.orders.collections.components.OrderWithItems;
import uz.orders.collections.components.OutgoWithItems;

import java.util.List;

public interface OutgoDAO {

    List<OutgoWithItems> getAll();
    OutgoWithItems getById(int id);
    void saveOutgo(OrderWithItems orderWithItems);
    void deleteOutgo(int id);

}
