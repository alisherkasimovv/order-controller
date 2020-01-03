package uz.orders.collections.components;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.orders.db.entities.registrars.Income;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IncomeWithItems  extends ItemList {

    private Income income;

}
