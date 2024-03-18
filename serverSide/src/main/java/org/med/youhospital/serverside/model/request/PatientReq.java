package org.med.youhospital.serverside.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientReq extends PersonReq {

    private LocalDate dateOfBirth;

    private String patientType;

    private UUID departmentId;

    private UUID roomId;

}
