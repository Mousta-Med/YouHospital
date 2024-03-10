package org.med.youhospital.serverside.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.med.youhospital.serverside.model.enums.RoomType;
import org.med.youhospital.serverside.model.request.DepartmentReq;
import org.med.youhospital.serverside.model.request.PatientReq;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomRes extends AuditableRes {


    private Integer roomNum;

    private String location;

    private RoomType roomType;

    private DepartmentReq department;

    private List<PatientReq> patient;

}
