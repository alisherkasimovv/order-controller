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
@Table(name = "db_items_order")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderItem extends ItemEntity {

    @Column(name = "is_provided")
    private boolean provided = false;

    @Column(name = "percent_provided")
    private int percent;

}
