package org.med.youhospital.serverside.controller;

import org.med.youhospital.serverside.model.request.RoomReq;
import org.med.youhospital.serverside.model.response.RoomRes;
import org.med.youhospital.serverside.service.RoomService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/room")
public class RoomController extends BaseController<RoomReq, UUID, RoomRes, RoomService> {
    protected RoomController(RoomService service) {
        super(service);
    }
}
