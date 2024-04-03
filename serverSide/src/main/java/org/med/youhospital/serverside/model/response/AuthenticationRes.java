package org.med.youhospital.serverside.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.med.youhospital.serverside.model.request.PatientReq;
import org.med.youhospital.serverside.model.request.PersonReq;
import org.med.youhospital.serverside.model.request.StaffReq;

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
