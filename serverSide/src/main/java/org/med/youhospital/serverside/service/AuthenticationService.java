package org.med.youhospital.serverside.service;


import org.med.youhospital.serverside.model.request.AuthenticationReq;
import org.med.youhospital.serverside.model.request.PatientReq;
import org.med.youhospital.serverside.model.response.AuthenticationRes;


public interface AuthenticationService {

    AuthenticationRes register(PatientReq patientReq);

    AuthenticationRes login(AuthenticationReq userDto);
}
