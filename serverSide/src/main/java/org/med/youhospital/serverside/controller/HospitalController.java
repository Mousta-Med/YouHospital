package org.med.youhospital.serverside.controller;

import org.med.youhospital.serverside.model.request.HospitalReq;
import org.med.youhospital.serverside.model.response.HospitalRes;
import org.med.youhospital.serverside.service.HospitalService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/hospital")
public class HospitalController extends BaseController<HospitalReq, UUID, HospitalRes, HospitalService> {
    protected HospitalController(HospitalService service) {
        super(service);
    }
}
