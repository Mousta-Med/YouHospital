package org.med.youhospital.serverside.service;

import org.med.youhospital.serverside.model.request.ExaminationReq;
import org.med.youhospital.serverside.model.request.StaffReq;
import org.med.youhospital.serverside.model.response.ExaminationRes;
import org.med.youhospital.serverside.model.response.StaffRes;

import java.util.UUID;

public interface ExaminationService extends BaseService<ExaminationReq, UUID, ExaminationRes> {
}
