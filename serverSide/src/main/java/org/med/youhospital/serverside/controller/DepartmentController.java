package org.med.youhospital.serverside.controller;

import org.med.youhospital.serverside.model.request.DepartmentReq;
import org.med.youhospital.serverside.model.response.DepartmentRes;
import org.med.youhospital.serverside.service.DepartmentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/department")
public class DepartmentController extends BaseController<DepartmentReq, UUID, DepartmentRes, DepartmentService> {
    protected DepartmentController(DepartmentService service) {
        super(service);
    }
}
