package org.med.youhospital.serverside.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.med.youhospital.serverside.model.request.PatientReq;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BillRes extends AuditableRes {

    private Double amount;

    private PatientReq patient;

}
