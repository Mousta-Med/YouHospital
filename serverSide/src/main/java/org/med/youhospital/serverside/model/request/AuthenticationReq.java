package org.med.youhospital.serverside.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationReq {

    @NotBlank(message = "email should be not Empty")
    private String email;

    @NotBlank(message = "password should be not Empty")
    private String password;
}
