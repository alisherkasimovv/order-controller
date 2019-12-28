package uz.orders.db.entities.items;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.orders.db.entities.base.ItemEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "registrar_income_items")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IncomeItem extends ItemEntity {

    @Column(name = "cost")
    private double cost;

    @Column(name = "amount")
    private double amount;

}
