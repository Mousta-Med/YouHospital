package org.med.youhospital.serverside.service.impl;

import org.med.youhospital.serverside.exception.ResourceNotFoundException;
import org.med.youhospital.serverside.model.entity.Department;
import org.med.youhospital.serverside.model.entity.Patient;
import org.med.youhospital.serverside.model.entity.Room;
import org.med.youhospital.serverside.model.request.PatientReq;
import org.med.youhospital.serverside.model.response.PatientRes;
import org.med.youhospital.serverside.repository.DepartmentRepository;
import org.med.youhospital.serverside.repository.PatientRepository;
import org.med.youhospital.serverside.repository.RoomRepository;
import org.med.youhospital.serverside.service.PatientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public PatientRes save(PatientReq patientReq) {
        Department department = departmentRepository.findById(patientReq.getDepartmentId()).orElseThrow(() -> new ResourceNotFoundException("Department Not found"));
        Room room = roomRepository.findById(patientReq.getRoomId()).orElseThrow(() -> new ResourceNotFoundException("Room Not found"));
        Patient patient = modelMapper.map(patientReq, Patient.class);
        patient.setPass(passwordEncoder.encode(patientReq.getPass()));
        patient.setDepartment(department);
        patient.setRoom(room);
        return modelMapper.map(patientRepository.save(patient), PatientRes.class);
    }

    @Override
    public List<PatientRes> findAll() {
        return patientRepository.findAll().stream().map(patient -> {
            PatientRes patientRes = modelMapper.map(patient, PatientRes.class);
            patientRes.setDepartmentId(patient.getDepartment().getId());
            patientRes.setRoomId(patient.getRoom().getId());
            return patientRes;
        }).collect(Collectors.toList());
    }

    @Override
    public PatientRes findOne(UUID id) {
        return patientRepository.findById(id)
                .map(patient -> {
                    PatientRes patientRes = modelMapper.map(patient, PatientRes.class);
                    patientRes.setDepartmentId(patient.getDepartment().getId());
                    patientRes.setRoomId(patient.getRoom().getId());
                    return patientRes;
                }).orElseThrow(() -> new ResourceNotFoundException("Patient Not found with this: " + id));
    }

    @Override
    public PatientRes update(UUID id, PatientReq patientReq) {
        Patient existingPatient = patientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patient Not found with this: " + id));
        Department department = departmentRepository.findById(patientReq.getDepartmentId()).orElseThrow(() -> new ResourceNotFoundException("Department Not found"));
        Room room = roomRepository.findById(patientReq.getRoomId()).orElseThrow(() -> new ResourceNotFoundException("Room Not found"));
        BeanUtils.copyProperties(patientReq, existingPatient);
        existingPatient.setId(id);
        existingPatient.setDepartment(department);
        existingPatient.setRoom(room);
        return modelMapper.map(patientRepository.save(existingPatient), PatientRes.class);
    }

    @Override
    public void delete(UUID id) {
        patientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patient Not found with this: " + id));
        patientRepository.deleteById(id);
    }
}
