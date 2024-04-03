package org.med.youhospital.serverside.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRes {

    private String token;

    private String role;

    private Optional<StaffRes> staff;

    private Optional<AdminRes> admin;

    private Optional<PatientRes> patient;

}
