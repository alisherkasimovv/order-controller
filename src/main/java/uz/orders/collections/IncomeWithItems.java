package uz.orders.collections;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.orders.collections.base.BaseItemList;
import uz.orders.db.entities.Income;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IncomeWithItems extends BaseItemList {

    private Income income;

}
