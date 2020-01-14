package uz.orders.collections;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.orders.db.entities.Product;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ItemCollection {

    private Product product;
    private double total;

}
