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
@Table(name = "db_products")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product extends UpdateBaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "cost" ,nullable = false)
    private double cost;

}
