package org.med.youhospital.serverside.model.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.med.youhospital.serverside.model.enums.Gender;
import org.med.youhospital.serverside.model.enums.IdentityType;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public abstract class Person extends Auditable implements UserDetails {

    protected String firstName;

    protected String lastName;

    protected Gender gender;

    protected String phone;

    protected String email;

    protected String pass;

    protected IdentityType identityType;

    protected String identityCode;

}
