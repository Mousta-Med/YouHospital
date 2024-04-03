package org.med.youhospital.serverside.controller;

import org.med.youhospital.serverside.model.request.OperationReq;
import org.med.youhospital.serverside.model.response.OperationRes;
import org.med.youhospital.serverside.service.OperationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/operation")
public class OperationController extends BaseController<OperationReq, UUID, OperationRes, OperationService> {
    protected OperationController(OperationService service) {
        super(service);
    }
}
