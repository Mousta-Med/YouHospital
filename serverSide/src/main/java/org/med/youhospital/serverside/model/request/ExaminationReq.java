package org.med.youhospital.serverside.model.request;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.med.youhospital.serverside.model.entity.Auditable;
import org.med.youhospital.serverside.model.entity.Patient;
import org.med.youhospital.serverside.model.entity.Staff;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExaminationReq extends Auditable {

    private String problem;

    private LocalDateTime dateTime;

    private UUID patientId;

    private UUID staffId;
}
