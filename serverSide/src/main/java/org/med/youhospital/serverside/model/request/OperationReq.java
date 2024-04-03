package org.med.youhospital.serverside.model.request;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.med.youhospital.serverside.model.response.AuthPatientRes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OperationReq {

    @FutureOrPresent(message = "date Should Be Present or future")
    @NotNull(message = "Date Should Not Be Null")
    private LocalDate date;

    //    @FutureOrPresent(message = "time Should Be Present or future")
    @NotNull(message = "time Should Not Be Null")
    private LocalTime time;

    @Positive(message = "duration should be positive")
    private Integer duration;

    @Positive(message = "duration should be positive")
    private Double cost;

    @NotNull(message = "patient Id should be not null")
    private UUID patientId;

    @NotNull(message = "staff Id should be not null")
    private UUID staffId;

    private AuthPatientRes patientRes;

}
