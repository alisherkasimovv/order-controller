package uz.orders.collections;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.orders.db.entities.Product;
import uz.orders.db.entities.Warehouse;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WarehouseWithProduct {

    private Warehouse warehouse;
    private Product product;

}
