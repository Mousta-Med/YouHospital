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

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Department extends Auditable {


    private String name;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private List<Staff> staffs;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private List<Patient> patients;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private List<Room> rooms;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Hospital hospital;
}
