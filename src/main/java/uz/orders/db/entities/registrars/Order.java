package uz.orders.db.entities.registrars;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.orders.db.entities.base.DocumentEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "db_orders")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order extends DocumentEntity {

    @Column(name = "is_full_provided")
    private boolean provided = false;

    @Column(name = "market_id")
    private int marketId;

}
