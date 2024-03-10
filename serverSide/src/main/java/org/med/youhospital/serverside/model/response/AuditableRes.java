package org.med.youhospital.serverside.model.response;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditableRes {

    protected String createdBy;
    protected LocalDateTime CreatedDate;
    protected String lastModifiedBy;
    protected LocalDateTime lastModifiedDate;
    private UUID id;
}
