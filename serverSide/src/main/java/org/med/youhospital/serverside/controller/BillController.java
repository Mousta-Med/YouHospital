package org.med.youhospital.serverside.controller;

import org.med.youhospital.serverside.model.request.BillReq;
import org.med.youhospital.serverside.model.response.BillRes;
import org.med.youhospital.serverside.service.BillService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/bill")
public class BillController extends BaseController<BillReq, UUID, BillRes, BillService> {
    protected BillController(BillService service) {
        super(service);
    }
}
