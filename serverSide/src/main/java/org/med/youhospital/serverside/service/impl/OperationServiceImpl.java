package org.med.youhospital.serverside.service.impl;

import org.med.youhospital.serverside.exception.ResourceNotFoundException;
import org.med.youhospital.serverside.model.entity.Auditable;
import org.med.youhospital.serverside.model.entity.Operation;
import org.med.youhospital.serverside.model.entity.Patient;
import org.med.youhospital.serverside.model.entity.Staff;
import org.med.youhospital.serverside.model.request.OperationReq;
import org.med.youhospital.serverside.model.response.OperationRes;
import org.med.youhospital.serverside.repository.OperationRepository;
import org.med.youhospital.serverside.repository.PatientRepository;
import org.med.youhospital.serverside.repository.StaffRepository;
import org.med.youhospital.serverside.service.OperationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OperationServiceImpl implements OperationService {

    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public OperationRes save(OperationReq operationReq) {
        Patient patient = patientRepository.findById(operationReq.getPatientId()).orElseThrow(() -> new ResourceNotFoundException("patient Not found"));
        Staff staff = staffRepository.findById(operationReq.getStaffId()).orElseThrow(() -> new ResourceNotFoundException("staff Not found"));
        Operation operation = modelMapper.map(operationReq, Operation.class);
        operation.setPatient(patient);
        operation.setStaff(staff);
        return modelMapper.map(operationRepository.save(operation), OperationRes.class);
    }

    @Override
    public List<OperationRes> findAll() {
        return operationRepository.findAll().stream().map(operation -> {
            OperationRes operationRes =  modelMapper.map(operation, OperationRes.class);
            operationRes.setPatientId(operation.getPatient().getId());
            operationRes.setStaffId(operation.getStaff().getId());
            return operationRes;
        }).collect(Collectors.toList());
    }

    @Override
    public OperationRes findOne(UUID id) {
        return operationRepository.findById(id)
                .map(operation -> {
                    OperationRes operationRes =  modelMapper.map(operation, OperationRes.class);
                    operationRes.setPatientId(operation.getPatient().getId());
                    operationRes.setStaffId(operation.getStaff().getId());
                    return operationRes;
                }).orElseThrow(() -> new ResourceNotFoundException("Operation Not found with this: " + id));
    }

    @Override
    public OperationRes update(UUID id, OperationReq operationReq) {
        Operation existingOperation = operationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Operation Not found with this: " + id));
        Patient patient = patientRepository.findById(operationReq.getPatientId()).orElseThrow(() -> new ResourceNotFoundException("patient Not found"));
        Staff staff = staffRepository.findById(operationReq.getStaffId()).orElseThrow(() -> new ResourceNotFoundException("staff Not found"));
        BeanUtils.copyProperties(operationReq, existingOperation);
        existingOperation.setId(id);
        existingOperation.setPatient(patient);
        existingOperation.setStaff(staff);
        return modelMapper.map(operationRepository.save(existingOperation), OperationRes.class);
    }

    @Override
    public void delete(UUID id) {
        operationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Operation Not found with this: " + id));
        operationRepository.deleteById(id);
    }

}
