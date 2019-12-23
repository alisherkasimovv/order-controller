package uz.orders.db.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.orders.db.entities.base.UpdateBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "db_markets")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Market extends UpdateBaseEntity {

    @Column(name = "name", unique = true)
    private String name;
}
