package uz.orders.db.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.orders.db.entities.base.OrderEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "db_outcomes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Outcome extends OrderEntity {

    @Column(name = "full_cost")
    private double cost;

}
