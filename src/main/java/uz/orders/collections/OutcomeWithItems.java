package uz.orders.collections;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.orders.collections.base.BaseItemList;
import uz.orders.db.entities.Outcome;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OutcomeWithItems extends BaseItemList {

    private Outcome outcome;

}
