package org.med.youhospital.serverside.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentReq {

    @NotBlank(message = "department name should be not blank")
    private String name;

    @NotNull(message = "HospitalId should be not nul")
    private UUID hospitalId;

}
