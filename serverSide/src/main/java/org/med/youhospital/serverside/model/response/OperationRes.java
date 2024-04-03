package org.med.youhospital.serverside.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.med.youhospital.serverside.model.request.PatientReq;
import org.med.youhospital.serverside.model.request.StaffReq;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OperationRes extends AuditableRes {


    private LocalDate date;

    private LocalTime time;

    private Integer duration;

    private Double cost;

    private PatientReq patient;

    private UUID patientId;

    private UUID staffId;

    private StaffReq staff;

}
