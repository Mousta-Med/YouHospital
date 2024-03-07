package org.med.youhospital.serverside.service;


import org.med.youhospital.serverside.model.request.AuthenticationReq;
import org.med.youhospital.serverside.model.response.AuthenticationResponse;


public interface AuthenticationService {

    //    AuthenticationResponse register(UserDto userDto);
    AuthenticationResponse login(AuthenticationReq userDto);
}
