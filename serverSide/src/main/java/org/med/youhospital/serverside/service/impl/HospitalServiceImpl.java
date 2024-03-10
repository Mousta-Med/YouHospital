package org.med.youhospital.serverside.service.impl;

import org.med.youhospital.serverside.exception.ResourceNotFoundException;
import org.med.youhospital.serverside.model.entity.Hospital;
import org.med.youhospital.serverside.model.request.HospitalReq;
import org.med.youhospital.serverside.model.response.HospitalRes;
import org.med.youhospital.serverside.repository.HospitalRepository;
import org.med.youhospital.serverside.service.HospitalService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public HospitalRes save(HospitalReq hospitalReq) {
        Hospital Hospital = modelMapper.map(hospitalReq, Hospital.class);
        return modelMapper.map(hospitalRepository.save(Hospital), HospitalRes.class);
    }

    @Override
    public List<HospitalRes> findAll() {
        return hospitalRepository.findAll().stream().map(Hospital -> modelMapper.map(Hospital, HospitalRes.class)).collect(Collectors.toList());
    }

    @Override
    public HospitalRes findOne(UUID id) {
        return hospitalRepository.findById(id)
                .map(Hospital -> modelMapper.map(Hospital, HospitalRes.class)).orElseThrow(() -> new ResourceNotFoundException("Hospital Not found with this: " + id));
    }

    @Override
    public HospitalRes update(UUID id, HospitalReq hospitalReq) {
        Hospital existingHospital = hospitalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hospital Not found with this: " + id));
        BeanUtils.copyProperties(hospitalReq, existingHospital);
        existingHospital.setId(id);
        return modelMapper.map(hospitalRepository.save(existingHospital), HospitalRes.class);
    }

    @Override
    public void delete(UUID id) {
        hospitalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hospital Not found with this: " + id));
        hospitalRepository.deleteById(id);
    }

}
