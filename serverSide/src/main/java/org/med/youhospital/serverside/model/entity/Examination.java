package org.med.youhospital.serverside.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Examination extends Auditable {

    private String problem;

    private LocalDateTime dateTime;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Patient patient;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Staff staff;
}
