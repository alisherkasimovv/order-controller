package uz.orders.db.entities.registrars;

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
@Table(name = "registrar_items")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Item extends UpdateBaseEntity {

    @Column(name = "product_id")
    private int productId;

    @Nullable
    @Column(name = "document_id")
    private int documentId;

    @Nullable
    @Column(name = "income_cost")
    private double incomeCost;

    @Nullable
    @Column(name = "income_quantity")
    private double incomeQuantity;

    @Nullable
    @Column(name = "income_amount")
    private double incomeAmount;

    @Nullable
    @Column(name = "outgoing_quantity")
    private double outgoQuantity;

    @Nullable
    @Column(name = "order_quantity")
    private double orderQuantity;

    @Nullable
    @Column(name = "is_provided")
    private boolean provided = false;

    @Nullable
    @Column(name = "percent_provided")
    private int percent;

    @Nullable
    @Column(name = "is_order")
    private boolean order = false;

    @Nullable
    @Column(name = "is_income")
    private boolean income = false;

    @Nullable
    @Column(name = "is_outgo")
    private boolean outgo = false;

}
