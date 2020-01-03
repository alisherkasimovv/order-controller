package uz.orders.collections.components;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.orders.db.entities.registrars.Outgo;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OutgoWithItems extends ItemList {

    Outgo outgo;

}
