package uz.orders.collections.components;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.orders.db.entities.registrars.Order;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderWithItems extends ItemList {

    private Order order;

}
