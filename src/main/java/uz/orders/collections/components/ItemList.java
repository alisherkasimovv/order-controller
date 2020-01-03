package uz.orders.collections.components;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.orders.db.entities.registrars.Item;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public abstract class ItemList {

    List<Item> items;

}
