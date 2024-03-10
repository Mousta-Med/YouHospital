package org.med.youhospital.serverside.controller;

import org.med.youhospital.serverside.model.request.PatientReq;
import org.med.youhospital.serverside.model.response.PatientRes;
import org.med.youhospital.serverside.service.PatientService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/patient")
public class PatientController extends BaseController<PatientReq, UUID, PatientRes, PatientService> {

    protected PatientController(PatientService service) {
        super(service);
    }
}
