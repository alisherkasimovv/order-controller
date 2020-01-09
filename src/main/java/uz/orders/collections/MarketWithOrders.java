package uz.orders.collections;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.orders.collections.components.OrderWithItems;
import uz.orders.db.entities.Market;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MarketWithOrders {

    Market market;
    List<OrderWithItems> orders;

}
