package uz.orders.collections.components;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.orders.db.entities.items.OrderItem;
import uz.orders.db.entities.registrars.Order;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderWithItems {

    private Order order;
    private List<OrderItem> items;

}
