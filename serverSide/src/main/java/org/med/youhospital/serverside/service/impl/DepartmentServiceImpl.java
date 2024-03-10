package org.med.youhospital.serverside.service.impl;

import org.med.youhospital.serverside.exception.ResourceNotFoundException;
import org.med.youhospital.serverside.model.entity.Department;
import org.med.youhospital.serverside.model.entity.Hospital;
import org.med.youhospital.serverside.model.request.DepartmentReq;
import org.med.youhospital.serverside.model.response.DepartmentRes;
import org.med.youhospital.serverside.repository.DepartmentRepository;
import org.med.youhospital.serverside.repository.HospitalRepository;
import org.med.youhospital.serverside.service.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public DepartmentRes save(DepartmentReq departmentReq) {
        Hospital hospital = hospitalRepository.findById(departmentReq.getHospitalId()).orElseThrow(() -> new ResourceNotFoundException("hospital Not found"));
        Department department = modelMapper.map(departmentReq, Department.class);
        department.setHospital(hospital);
        return modelMapper.map(departmentRepository.save(department), DepartmentRes.class);
    }

    @Override
    public List<DepartmentRes> findAll() {
        return departmentRepository.findAll().stream().map(Department -> modelMapper.map(Department, DepartmentRes.class)).collect(Collectors.toList());
    }

    @Override
    public DepartmentRes findOne(UUID id) {
        return departmentRepository.findById(id)
                .map(Department -> modelMapper.map(Department, DepartmentRes.class)).orElseThrow(() -> new ResourceNotFoundException("Department Not found with this: " + id));
    }

    @Override
    public DepartmentRes update(UUID id, DepartmentReq departmentReq) {
        Department existingDepartment = departmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Department Not found with this: " + id));
        Hospital hospital = hospitalRepository.findById(departmentReq.getHospitalId()).orElseThrow(() -> new ResourceNotFoundException("hospital Not found"));
        BeanUtils.copyProperties(departmentReq, existingDepartment);
        existingDepartment.setId(id);
        existingDepartment.setHospital(hospital);
        return modelMapper.map(departmentRepository.save(existingDepartment), DepartmentRes.class);
    }

    @Override
    public void delete(UUID id) {
        departmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Department Not found with this: " + id));
        departmentRepository.deleteById(id);
    }

}
