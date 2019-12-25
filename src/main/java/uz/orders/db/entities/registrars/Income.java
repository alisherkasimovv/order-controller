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
@Table(name = "db_incomes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Income extends DocumentEntity {

    @Column(name = "full_cost")
    private double cost;

}
