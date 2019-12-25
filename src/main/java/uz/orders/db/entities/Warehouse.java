package uz.orders.db.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.orders.db.entities.base.UpdateBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "db_warehouse")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Warehouse extends UpdateBaseEntity {

    @Column(name = "product_id")
    private int productId;

    @Column(name = "cost")
    private double cost;

    @Column(name = "quantity")
    private double quantity;

    @Column(name = "amount_of_money")
    private double amount;

}
