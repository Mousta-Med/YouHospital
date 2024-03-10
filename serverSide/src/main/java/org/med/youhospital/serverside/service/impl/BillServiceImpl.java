package org.med.youhospital.serverside.service.impl;

import org.med.youhospital.serverside.exception.ResourceNotFoundException;
import org.med.youhospital.serverside.model.entity.Bill;
import org.med.youhospital.serverside.model.entity.Patient;
import org.med.youhospital.serverside.model.request.BillReq;
import org.med.youhospital.serverside.model.response.BillRes;
import org.med.youhospital.serverside.repository.BillRepository;
import org.med.youhospital.serverside.repository.PatientRepository;
import org.med.youhospital.serverside.service.BillService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public BillRes save(BillReq billReq) {
        Patient patient = patientRepository.findById(billReq.getPatientId()).orElseThrow(() -> new ResourceNotFoundException("Patient Not found"));
        Bill Bill = modelMapper.map(billReq, Bill.class);
        Bill.setPatient(patient);
        return modelMapper.map(billRepository.save(Bill), BillRes.class);
    }

    @Override
    public List<BillRes> findAll() {
        return billRepository.findAll().stream().map(Bill -> modelMapper.map(Bill, BillRes.class)).collect(Collectors.toList());
    }

    @Override
    public BillRes findOne(UUID id) {
        return billRepository.findById(id)
                .map(Bill -> modelMapper.map(Bill, BillRes.class)).orElseThrow(() -> new ResourceNotFoundException("Bill Not found with this: " + id));
    }

    @Override
    public BillRes update(UUID id, BillReq billReq) {
        Bill existingBill = billRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Bill Not found with this: " + id));
        Patient patient = patientRepository.findById(billReq.getPatientId()).orElseThrow(() -> new ResourceNotFoundException("Patient Not found"));
        BeanUtils.copyProperties(billReq, existingBill);
        existingBill.setId(id);
        existingBill.setPatient(patient);
        return modelMapper.map(billRepository.save(existingBill), BillRes.class);
    }

    @Override
    public void delete(UUID id) {
        billRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Bill Not found with this: " + id));
        billRepository.deleteById(id);
    }
}
