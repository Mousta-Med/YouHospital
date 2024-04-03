package org.med.youhospital.serverside.service;

import org.med.youhospital.serverside.model.request.ExaminationReq;
import org.med.youhospital.serverside.model.response.ExaminationRes;

import java.util.UUID;

public interface ExaminationService extends BaseService<ExaminationReq, UUID, ExaminationRes> {
}
