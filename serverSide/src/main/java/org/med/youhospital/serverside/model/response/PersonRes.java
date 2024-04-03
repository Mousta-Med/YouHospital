package org.med.youhospital.serverside.model.response;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.med.youhospital.serverside.model.enums.Gender;
import org.med.youhospital.serverside.model.enums.IdentityType;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public class PersonRes extends AuditableRes {

    private String firstName;

    private String lastName;

    private Gender gender;

    private String phone;

    private String email;

    private String pass;

    private IdentityType identityType;

    private String identityCode;

}
