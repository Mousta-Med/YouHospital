package org.med.youhospital.serverside.controller;

import org.med.youhospital.serverside.model.request.ExaminationReq;
import org.med.youhospital.serverside.model.response.ExaminationRes;
import org.med.youhospital.serverside.service.ExaminationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/examination")
public class ExaminationController extends BaseController<ExaminationReq, UUID, ExaminationRes, ExaminationService> {
    protected ExaminationController(ExaminationService service) {
        super(service);
    }
}
