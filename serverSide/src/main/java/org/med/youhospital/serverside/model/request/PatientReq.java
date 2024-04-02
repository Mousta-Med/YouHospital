package org.med.youhospital.serverside.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.med.youhospital.serverside.model.response.PersonRes;

import java.time.LocalDate;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientReq extends PersonReq {

    @NotNull(message = "dateOfBirth should be not null")
    private LocalDate dateOfBirth;

    @NotBlank(message = "patientType should be Not Blank")
    private String patientType;

    @NotNull(message = "departmentId should be not null")
    private UUID departmentId;

    @NotNull(message = "roomId should be not null")
    private UUID roomId;

}
