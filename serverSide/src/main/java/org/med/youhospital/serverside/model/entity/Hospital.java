package org.med.youhospital.serverside.model.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Hospital extends Auditable{


    private String name;

    private String address;

    private String phone;

    @OneToMany(mappedBy = "hospital", fetch = FetchType.LAZY)
    private List<Department> departments;
}
