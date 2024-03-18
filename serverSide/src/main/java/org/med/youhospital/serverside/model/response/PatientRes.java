package org.med.youhospital.serverside.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.med.youhospital.serverside.model.request.*;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientRes extends PersonRes {

    private LocalDate dateOfBirth;

    private String patientType;

    private List<BillReq> bills;

    private List<OperationReq> operations;

    private List<RecipeReq> recipes;

    private List<ExaminationReq> examinations;

    private DepartmentReq department;

    private RoomReq room;

}
