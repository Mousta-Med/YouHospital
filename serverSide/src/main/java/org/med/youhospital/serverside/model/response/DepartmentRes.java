package org.med.youhospital.serverside.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.med.youhospital.serverside.model.request.HospitalReq;
import org.med.youhospital.serverside.model.request.PatientReq;
import org.med.youhospital.serverside.model.request.RoomReq;
import org.med.youhospital.serverside.model.request.StaffReq;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentRes extends AuditableRes {


    private String name;

    private List<StaffReq> staff;

    private List<PatientReq> patient;

    private List<RoomReq> room;

    private HospitalReq hospital;
}
