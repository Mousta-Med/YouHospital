package org.med.youhospital.serverside.model.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class RecipeReq {

    @NotBlank(message = "medication should be not blank")
    private String medications;

    @NotBlank(message = "instructions should be not blank")
    private String instructions;

    @FutureOrPresent(message = "startDate should be in future or present")
    private LocalDate startDate;

    @Future(message = "endDate should be in future")
    private LocalDate endDate;

    @NotNull(message = "patientId should be not null")
    private UUID patientId;

    @NotNull(message = "staffId should be not null")
    private UUID staffId;

}
