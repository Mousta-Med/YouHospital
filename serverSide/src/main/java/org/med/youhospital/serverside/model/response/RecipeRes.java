package org.med.youhospital.serverside.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.med.youhospital.serverside.model.request.PatientReq;
import org.med.youhospital.serverside.model.request.StaffReq;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecipeRes extends AuditableRes {


    private String Medications;

    private String instructions;

    private LocalDate startDate;

    private LocalDate endDate;

    private PatientReq patient;

    private StaffReq staff;

}
