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
@Table(name = "db_items")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Item extends UpdateBaseEntity {

    @Column(name = "product_id")
    private int productId;

    @Nullable
    @Column(name = "document_id")
    private int documentId;

    @Nullable
    @Column(name = "product_cost")
    private double cost;

    @Column(name = "quantity")
    private double quantity;

    @Column(name = "amount")
    private double amount;

}
