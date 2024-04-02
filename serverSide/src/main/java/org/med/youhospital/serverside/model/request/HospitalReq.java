package org.med.youhospital.serverside.model.request;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HospitalReq {

    @NotBlank(message = "hospital name should be not blank")
    private String name;

    @NotBlank(message = "hospital address should be not blank")
    private String address;

    @NotBlank(message = "hospital phone should be not blank")
    private String phone;
}
