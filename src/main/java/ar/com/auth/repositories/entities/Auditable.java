package ar.com.auth.repositories.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public abstract class Auditable extends AuditableBasic {

  @CreatedBy
  @Column(name = "created_by")
  protected String createdBy;
  @LastModifiedBy
  @Column(name = "last_update_by")
  protected String lastUpdateBy;
}