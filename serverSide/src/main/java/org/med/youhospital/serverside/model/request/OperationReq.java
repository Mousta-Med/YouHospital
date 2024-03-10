package org.med.youhospital.serverside.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OperationReq {


    private LocalDate date;

    private LocalTime time;

    private Integer duration;

    private Double cost;

    private UUID patientId;

    private UUID staffId;

}
