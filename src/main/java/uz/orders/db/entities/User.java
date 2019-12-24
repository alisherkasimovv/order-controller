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
@Table(name = "db_users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User extends UpdateBaseEntity {

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "market_id")
    private int marketId;



}
