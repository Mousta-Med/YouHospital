package org.med.youhospital.serverside.model.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.med.youhospital.serverside.model.request.DepartmentReq;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HospitalRes extends AuditableRes {


    private String name;

    private String address;

    private String phone;

    private List<DepartmentReq> department;
}
