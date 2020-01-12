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
@Table(name = "db_users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User extends UpdateBaseEntity {

    @Column(name = "username", unique = true)
    private String username;

    @Nullable
    @Column(name = "first_name")
    private String firstName;

    @Nullable
    @Column(name = "last_name")
    private String lastName;

    @Nullable
    @Column(name = "market_id")
    private int marketId;

    @Column(name = "role")
    private String role;

    @Column(name = "password", nullable = false)
    private String password;


}
