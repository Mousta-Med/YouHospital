package org.med.youhospital.serverside.service;

import org.med.youhospital.serverside.model.request.OperationReq;
import org.med.youhospital.serverside.model.response.ExaminationRes;
import org.med.youhospital.serverside.model.response.OperationRes;

import java.util.List;
import java.util.UUID;

public interface OperationService extends BaseService<OperationReq, UUID, OperationRes> {
}
