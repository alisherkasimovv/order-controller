package uz.orders.db.entities.base;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public abstract class OrderEntity extends UpdateBaseEntity {

    @Column(name = "reference_number", unique = true)
    private String reference;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "customer_id")
    private int customerId;

}
