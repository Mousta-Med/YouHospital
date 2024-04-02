package org.med.youhospital.serverside.service.impl;

import org.med.youhospital.serverside.exception.ResourceNotFoundException;
import org.med.youhospital.serverside.model.entity.Examination;
import org.med.youhospital.serverside.model.entity.Patient;
import org.med.youhospital.serverside.model.entity.Staff;
import org.med.youhospital.serverside.model.request.ExaminationReq;
import org.med.youhospital.serverside.model.response.ExaminationRes;
import org.med.youhospital.serverside.repository.ExaminationRepository;
import org.med.youhospital.serverside.repository.PatientRepository;
import org.med.youhospital.serverside.repository.StaffRepository;
import org.med.youhospital.serverside.service.ExaminationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ExaminationServiceImpl implements ExaminationService {

    @Autowired
    private ExaminationRepository examinationRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public ExaminationRes save(ExaminationReq examinationReq) {
        Patient patient = patientRepository.findById(examinationReq.getPatientId()).orElseThrow(() -> new ResourceNotFoundException("patient Not found"));
        Staff staff = staffRepository.findById(examinationReq.getStaffId()).orElseThrow(() -> new ResourceNotFoundException("staff Not found"));
        Examination examination = modelMapper.map(examinationReq, Examination.class);
        examination.setPatient(patient);
        examination.setStaff(staff);
        return modelMapper.map(examinationRepository.save(examination), ExaminationRes.class);
    }

    @Override
    public List<ExaminationRes> findAll() {
        return examinationRepository.findAll().stream().map(examination -> {
            ExaminationRes examinationRes = modelMapper.map(examination, ExaminationRes.class);
            examinationRes.setPatientId(examination.getPatient().getId());
            examinationRes.setStaffId(examination.getStaff().getId());
            return examinationRes;
        }).collect(Collectors.toList());
    }

    @Override
    public ExaminationRes findOne(UUID id) {
        return examinationRepository.findById(id)
                .map(examination -> {
                    ExaminationRes examinationRes = modelMapper.map(examination, ExaminationRes.class);
                    examinationRes.setPatientId(examination.getPatient().getId());
                    examinationRes.setStaffId(examination.getStaff().getId());
                    return examinationRes;
                }).orElseThrow(() -> new ResourceNotFoundException("Examination Not found with this: " + id));
    }

    @Override
    public ExaminationRes update(UUID id, ExaminationReq examinationReq) {
        Patient patient = patientRepository.findById(examinationReq.getPatientId()).orElseThrow(() -> new ResourceNotFoundException("patient Not found"));
        Examination existingExamination = examinationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Examination Not found with this: " + id));
        Staff staff = staffRepository.findById(examinationReq.getStaffId()).orElseThrow(() -> new ResourceNotFoundException("staff Not found"));
        existingExamination.setId(id);
        existingExamination.setPatient(patient);
        existingExamination.setStaff(staff);
        return modelMapper.map(examinationRepository.save(existingExamination), ExaminationRes.class);
    }

    @Override
    public void delete(UUID id) {
        examinationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Examination Not found with this: " + id));
        examinationRepository.deleteById(id);
    }

}
