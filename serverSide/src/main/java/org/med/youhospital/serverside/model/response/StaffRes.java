package org.med.youhospital.serverside.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.med.youhospital.serverside.model.enums.StaffRole;
import org.med.youhospital.serverside.model.request.DepartmentReq;
import org.med.youhospital.serverside.model.request.ExaminationReq;
import org.med.youhospital.serverside.model.request.OperationReq;
import org.med.youhospital.serverside.model.request.RecipeReq;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StaffRes extends PersonRes {

    private LocalDate recruitmentDate;

    private String address;

    private String specialization;

    private StaffRole role;

    private DepartmentReq department;

    private UUID departmentId;

    private List<OperationReq> operations;

    private List<RecipeReq> recipes;

    private List<ExaminationReq> examinations;

}