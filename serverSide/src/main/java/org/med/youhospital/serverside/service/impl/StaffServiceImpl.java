package org.med.youhospital.serverside.service.impl;

import org.med.youhospital.serverside.exception.ResourceNotFoundException;
import org.med.youhospital.serverside.model.entity.Admin;
import org.med.youhospital.serverside.model.entity.Department;
import org.med.youhospital.serverside.model.entity.Staff;
import org.med.youhospital.serverside.model.request.StaffReq;
import org.med.youhospital.serverside.model.response.StaffRes;
import org.med.youhospital.serverside.repository.AdminRepository;
import org.med.youhospital.serverside.repository.DepartmentRepository;
import org.med.youhospital.serverside.repository.StaffRepository;
import org.med.youhospital.serverside.service.StaffService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public StaffRes save(StaffReq staffReq) {
        Department department = departmentRepository.findById(staffReq.getDepartmentId()).orElseThrow(() -> new ResourceNotFoundException("Department Not found"));
        Admin admin = adminRepository.findById(staffReq.getAdminId()).orElseThrow(() -> new ResourceNotFoundException("Admin Not found"));
        Staff staff = modelMapper.map(staffReq, Staff.class);
        staff.setDepartment(department);
        staff.setAdmin(admin);
        return modelMapper.map(staffRepository.save(staff), StaffRes.class);
    }

    @Override
    public List<StaffRes> findAll() {
        return staffRepository.findAll().stream().map(Staff -> modelMapper.map(Staff, StaffRes.class)).collect(Collectors.toList());
    }

    @Override
    public StaffRes findOne(UUID id) {
        return staffRepository.findById(id)
                .map(Staff -> modelMapper.map(Staff, StaffRes.class)).orElseThrow(() -> new ResourceNotFoundException("Staff Not found with this: " + id));
    }

    @Override
    public StaffRes update(UUID id, StaffReq staffReq) {
        Staff existingStaff = staffRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Staff Not found with this: " + id));
        Department department = departmentRepository.findById(staffReq.getDepartmentId()).orElseThrow(() -> new ResourceNotFoundException("Department Not found"));
        Admin admin = adminRepository.findById(staffReq.getAdminId()).orElseThrow(() -> new ResourceNotFoundException("Admin Not found"));
        BeanUtils.copyProperties(staffReq, existingStaff);
        existingStaff.setId(id);
        existingStaff.setDepartment(department);
        existingStaff.setAdmin(admin);
        return modelMapper.map(staffRepository.save(existingStaff), StaffRes.class);
    }

    @Override
    public void delete(UUID id) {
        staffRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Staff Not found with this: " + id));
        staffRepository.deleteById(id);
    }

}
