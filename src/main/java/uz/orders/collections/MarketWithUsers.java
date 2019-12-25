package uz.orders.collections;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.orders.db.entities.Market;
import uz.orders.db.entities.User;
import uz.orders.db.entities.base.BaseEntity;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MarketWithUsers extends BaseEntity {

    private Market market;
    List<User> users;

}
