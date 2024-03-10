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
public final class PatientRes extends PersonRes {

    private LocalDate dateOfBirth;

    private String patientType;

    private String problem;

    private List<BillReq> bill;

    private List<OperationReq> operation;

    private List<RecipeReq> recipes;

    private DepartmentReq department;

    private RoomReq room;

}
