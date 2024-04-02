package org.med.youhospital.serverside.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.med.youhospital.serverside.model.enums.Status;
import org.med.youhospital.serverside.model.request.PatientReq;

import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BillRes extends AuditableRes {

    private Double amount;

    private Status status;

    private PatientReq patient;

    private UUID patientId;


}
