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
public class RecipeReq {


    private String Medications;

    private String instructions;

    private LocalDate startDate;

    private LocalDate endDate;

    private UUID patientId;

    private UUID staffId;

}
