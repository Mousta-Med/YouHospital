package org.med.youhospital.serverside.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.med.youhospital.serverside.model.enums.Status;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bill extends Auditable {


    private Double amount;

    private Status status;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Patient patient;

}
