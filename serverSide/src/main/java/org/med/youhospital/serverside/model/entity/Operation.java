package org.med.youhospital.serverside.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Operation extends Auditable {


    private LocalDate date;

    private LocalTime time;

    private Integer duration;

    private Double cost;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Patient patient;

//    @ManyToOne
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private Staff staff;

    @OneToMany(mappedBy = "operation", fetch = FetchType.LAZY)
    private List<Staff> staffs;

}
