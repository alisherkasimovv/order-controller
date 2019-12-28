package uz.orders.db.entities.base;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
public abstract class ItemEntity extends UpdateBaseEntity {

    @Column(name = "product_id")
    private int productId;

    @Nullable
    @Column(name = "document_id")
    private int documentId;

    @Column(name = "quantity")
    private double quantity;

}
