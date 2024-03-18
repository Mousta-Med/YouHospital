package org.med.youhospital.serverside.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.med.youhospital.serverside.model.enums.StaffRole;
import org.med.youhospital.serverside.model.response.PersonRes;

import java.time.LocalDate;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StaffReq extends PersonRes {

    private LocalDate recruitmentDate;

    private String address;

    private String specialization;

    private StaffRole role;

    private UUID departmentId;

    private UUID adminId;

    private UUID operationId;

}
