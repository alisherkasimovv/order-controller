package uz.orders.db.entities.registrars;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;
import uz.orders.db.entities.base.DocumentEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "db_outcomes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Outcome extends DocumentEntity {

    @Column(name = "full_cost")
    private double cost;

    @Nullable
    @Column(name = "market_id")
    private int marketId;

    @Nullable
    @Column(name = "linked_document_id")
    private int linkedDocumentId;

}
