package org.med.youhospital.serverside.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.med.youhospital.serverside.model.request.DepartmentReq;
import org.med.youhospital.serverside.model.request.RoomReq;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthPatientRes extends PersonRes {

    private LocalDate dateOfBirth;

    private String patientType;

    private UUID departmentId;

    private UUID roomId;

    private DepartmentReq department;

    private RoomReq room;
}
