package org.med.youhospital.serverside.model.request;

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
public abstract class PersonReq {

    protected String firstName;

    protected String lastName;

    protected Gender gender;

    protected String phone;

    protected String email;

    protected String pass;

    protected IdentityType identityType;

    protected String identityCode;

}
