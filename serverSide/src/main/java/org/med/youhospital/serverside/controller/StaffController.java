package org.med.youhospital.serverside.controller;

import org.med.youhospital.serverside.model.request.StaffReq;
import org.med.youhospital.serverside.model.response.StaffRes;
import org.med.youhospital.serverside.service.StaffService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/staff")
public class StaffController extends BaseController<StaffReq, UUID, StaffRes, StaffService> {
    protected StaffController(StaffService service) {
        super(service);
    }
}
