package org.med.youhospital.serverside.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.med.youhospital.serverside.model.enums.StaffRole;
import org.med.youhospital.serverside.model.response.PersonRes;

import java.time.LocalDate;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StaffReq extends PersonReq {

    private LocalDate recruitmentDate;

    @NotBlank(message = "address should be not blank")
    private String address;

    @NotBlank(message = "specialization should be not blank")
    private String specialization;

    private StaffRole role;

    @NotNull(message = "adminId should be not null")
    private UUID adminId;

    private UUID departmentId;

}
