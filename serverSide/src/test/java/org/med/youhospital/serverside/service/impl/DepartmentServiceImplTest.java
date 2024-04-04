package org.med.youhospital.serverside.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.med.youhospital.serverside.model.entity.Department;
import org.med.youhospital.serverside.model.entity.Hospital;
import org.med.youhospital.serverside.model.request.DepartmentReq;
import org.med.youhospital.serverside.model.response.DepartmentRes;
import org.med.youhospital.serverside.repository.DepartmentRepository;
import org.med.youhospital.serverside.repository.HospitalRepository;
import org.med.youhospital.serverside.service.DepartmentService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {
    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private HospitalRepository hospitalRepository;

    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private DepartmentService departmentService = new DepartmentServiceImpl();

    DepartmentReq departmentReq;

    DepartmentRes departmentRes;
    Department department;

    Department department1;

    List<Department> departments;

    Hospital hospital;



    @BeforeEach
    void setUp() {
        hospital = new Hospital();
        hospital.setId(UUID.randomUUID());
        departmentRes = new DepartmentRes();
        departmentReq = new DepartmentReq();
        departmentReq.setHospitalId(hospital.getId());
        department = new Department();
        department.setId(UUID.randomUUID());
        department.setName("Department");
        department1 = new Department();
        department1.setId(UUID.randomUUID());
        department1.setName("Department 1");
        departments = Arrays.asList(department, department1);
    }

    @Test
    void testSave() {
        // Mocking hospital retrieval
        when(hospitalRepository.findById(hospital.getId())).thenReturn(Optional.of(hospital));
        // Mocking department mapping
        when(modelMapper.map(departmentReq, Department.class)).thenReturn(department);
        // Mocking department save
        when(departmentRepository.save(department)).thenReturn(department);
        // Mocking department response mapping
        when(modelMapper.map(department, DepartmentRes.class)).thenReturn(departmentRes);
        // Calling the service method
        DepartmentRes result = departmentService.save(departmentReq);
        // Verifying that hospitalRepository.findById() was called
        // Verifying that modelMapper.map() was called to map the request to department
        // Verifying that departmentRepository.save() was called with the correct department
        // Verifying that modelMapper.map() was called to map the saved department to response
        verify(hospitalRepository).findById(hospital.getId());
        verify(modelMapper).map(departmentReq, Department.class);
        verify(departmentRepository).save(department);
        verify(modelMapper).map(department, DepartmentRes.class);
        // Verifying the result
        assertEquals(departmentRes, result);
    }

    @Test
    void testFindAll() {
        // Mocking the behavior of departmentRepository.findAll()
        when(departmentRepository.findAll()).thenReturn(departments);
        // Mocking the behavior of modelMapper.map()
        when(modelMapper.map(department, DepartmentRes.class)).thenReturn(new DepartmentRes());
        when(modelMapper.map(department1, DepartmentRes.class)).thenReturn(new DepartmentRes());
        // Calling the service method
        departmentService.findAll();
        // Verifying that departmentRepository.findAll() was called
        // Verifying that modelMapper.map() was called for each department
        verify(departmentRepository).findAll();
        verify(modelMapper).map(department, DepartmentRes.class);
        verify(modelMapper).map(department1, DepartmentRes.class);
    }

    @Test
    void testFindOne() {
        // Mocking the behavior of departmentRepository.findById()
        when(departmentRepository.findById(department.getId())).thenReturn(Optional.of(department));
        // Mocking the behavior of modelMapper.map()
        DepartmentRes departmentRes = new DepartmentRes();
        when(modelMapper.map(department, DepartmentRes.class)).thenReturn(departmentRes);
        // Calling the service method
        DepartmentRes result = departmentService.findOne(department.getId());
        // Verifying that departmentRepository.findById() was called
        // Verifying that modelMapper.map() was called
        verify(departmentRepository).findById(department.getId());
        verify(modelMapper).map(department, DepartmentRes.class);
        // Verifying the result
        assertEquals(departmentRes, result);
    }

    @Test
    void testUpdate() {
        // Mocking department request
        departmentReq.setHospitalId(hospital.getId());
        // Mocking existing department
        Department existingDepartment = new Department();
        existingDepartment.setId(department.getId());
        when(departmentRepository.findById(department.getId())).thenReturn(Optional.of(existingDepartment));
        // Mocking hospital retrieval
        when(hospitalRepository.findById(hospital.getId())).thenReturn(Optional.of(hospital));
        // Mocking department update
        Department updatedDepartment = new Department();
        when(departmentRepository.save(existingDepartment)).thenReturn(updatedDepartment);
        // Mocking department response mapping
        when(modelMapper.map(updatedDepartment, DepartmentRes.class)).thenReturn(departmentRes);
        // Calling the service method
        DepartmentRes result = departmentService.update(department.getId(), departmentReq);
        // Verifying that departmentRepository.findById() was called
        // Verifying that hospitalRepository.findById() was called
        // Verifying that setId() and setHospital() were called on existing department
        // Verifying that departmentRepository.save() was called with the correct department
        // Verifying that modelMapper.map() was called to map the updated department to response
        verify(departmentRepository).findById(department.getId());
        verify(hospitalRepository).findById(hospital.getId());
        existingDepartment.setId(department.getId());
        existingDepartment.setHospital(hospital);
        verify(departmentRepository).save(existingDepartment);
        verify(modelMapper).map(updatedDepartment, DepartmentRes.class);
        // Verifying the result
        assertEquals(departmentRes, result);
    }

    @Test
    void testDelete() {
        // Mocking the behavior of departmentRepository.findById() to return a department
        when(departmentRepository.findById(department.getId())).thenReturn(Optional.of(new Department()));
        // Calling the service method
        departmentService.delete(department.getId());
        // Verifying that departmentRepository.findById() was called
        verify(departmentRepository).findById(department.getId());
        // Verifying that departmentRepository.deleteById() was called
        verify(departmentRepository).deleteById(department.getId());
    }
}