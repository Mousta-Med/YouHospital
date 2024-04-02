package org.med.youhospital.serverside.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.med.youhospital.serverside.model.enums.Status;

import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BillReq {

    @NotNull(message = "amount should be not null")
    private Double amount;

    private Status status;

    @NotNull(message = "patientId should be not null")
    private UUID patientId;

}
