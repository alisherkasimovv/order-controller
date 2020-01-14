package uz.orders.db.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;
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

    @Nullable
    @Column(name = "quantity_in_stock")
    private double quantityInStock;

    @Nullable
    @Column(name = "quantity_ordered")
    private double quantityOrdered;

    @Nullable
    @Column(name = "quantity_income")
    private double quantityIncome;

    /*
    quantityOrdered     10  5   10  0
    quantityIncome      0   5   0   10
    quantityInStock     0   5   5   15
     */

}
