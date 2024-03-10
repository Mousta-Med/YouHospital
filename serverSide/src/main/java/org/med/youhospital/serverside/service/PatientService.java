package org.med.youhospital.serverside.service;

import org.med.youhospital.serverside.model.request.PatientReq;
import org.med.youhospital.serverside.model.response.PatientRes;

import java.util.UUID;

public interface PatientService extends BaseService<PatientReq, UUID, PatientRes> {
}
