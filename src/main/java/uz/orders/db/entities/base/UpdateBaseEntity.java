package uz.orders.db.entities.base;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;
import java.util.Objects;

@MappedSuperclass
@Getter
@Setter
public abstract class UpdateBaseEntity extends BaseEntity {

    @Column(name = "created_at", updatable = false)
//    @CreatedDate
    private Date createdAt = new Date();

    @Column(name = "updated_at")
//    @LastModifiedDate
    private Date updatedAt;

    @Column(name = "is_deleted")
    private boolean deleted = false;

    @PrePersist
    public void prePersist() {
        this.createdAt = Objects.nonNull(this.createdAt) ? this.createdAt : new Date();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = Objects.nonNull(this.updatedAt) ? this.updatedAt : new Date();
    }

}
