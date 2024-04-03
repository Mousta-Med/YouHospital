package org.med.youhospital.serverside.controller;


import jakarta.validation.Valid;
import org.med.youhospital.serverside.model.request.AuthenticationReq;
import org.med.youhospital.serverside.model.request.PatientReq;
import org.med.youhospital.serverside.model.response.AuthenticationRes;
import org.med.youhospital.serverside.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationRes> register(@RequestBody @Valid PatientReq userDto) {
        return ResponseEntity.ok(authenticationService.register(userDto));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationRes> login(@RequestBody @Valid AuthenticationReq authenticationReq) {
        return ResponseEntity.ok(authenticationService.login(authenticationReq));
    }
}
