package org.med.youhospital.serverside.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.med.youhospital.serverside.model.entity.Auditable;
import org.med.youhospital.serverside.model.request.PatientReq;
import org.med.youhospital.serverside.model.request.StaffReq;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExaminationRes extends Auditable {

    private String problem;

    private LocalDateTime dateTime;

    private PatientReq patient;

    private StaffReq staff;
}
