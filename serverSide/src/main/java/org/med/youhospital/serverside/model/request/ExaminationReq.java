package org.med.youhospital.serverside.model.request;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.med.youhospital.serverside.model.entity.Auditable;
import org.med.youhospital.serverside.model.response.PersonRes;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExaminationReq extends Auditable {

    @NotBlank(message = "problem should be not blank")
    private String problem;

    @FutureOrPresent(message = "date Should Be Present or future")
    @NotNull(message = "Date Should Not Be Null")
    private LocalDateTime dateTime;

    @NotNull(message = "PatientId Should Not Be Null")
    private UUID patientId;

    @NotNull(message = "StaffId Should Not Be Null")
    private UUID staffId;

    private PersonRes patientRes;

    private PersonRes staffRes;
}
