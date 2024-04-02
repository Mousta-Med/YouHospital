package org.med.youhospital.serverside.model.request;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OperationReq {

    @FutureOrPresent(message = "date Should Be Present or future")
    @NotNull(message = "Date Should Not Be Null")
    private LocalDate date;

    @FutureOrPresent(message = "time Should Be Present or future")
    @NotNull(message = "time Should Not Be Null")
    private LocalTime time;

    @Positive(message = "duration should be positive")
    private Integer duration;

    @Positive(message = "duration should be positive")
    private Double cost;

    @NotNull(message = "patientId should be not null")
    private UUID patientId;

    @NotNull(message = "staffsId should be not null")
    private List<UUID> staffsId;

}
