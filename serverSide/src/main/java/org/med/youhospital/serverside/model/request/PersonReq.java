package org.med.youhospital.serverside.model.request;

import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.med.youhospital.serverside.model.enums.Gender;
import org.med.youhospital.serverside.model.enums.IdentityType;

import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public class PersonReq {

    private UUID id;

    @NotBlank(message = "first name should be not blank")
    private String firstName;

    @NotBlank(message = "last Name should be not blank")
    private String lastName;

    private Gender gender;

    @NotBlank(message = "phone should be not blank")
    private String phone;

    @NotBlank(message = "email should be not blank")
    private String email;

    @NotBlank(message = "pass should be not blank")
    private String pass;

    private IdentityType identityType;

    @NotBlank(message = "identityCode should be not blank")
    private String identityCode;

}
