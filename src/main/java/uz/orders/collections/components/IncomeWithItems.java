package uz.orders.collections.components;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.orders.db.entities.items.IncomeItem;
import uz.orders.db.entities.registrars.Income;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IncomeWithItems {

    private Income income;
    private List<IncomeItem> items;

}
