package org.med.youhospital.serverside.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.med.youhospital.serverside.controller.DepartmentController;
import org.med.youhospital.serverside.jwt.JWTAuthenticationFilter;
import org.med.youhospital.serverside.jwt.JWTUtil;
import org.med.youhospital.serverside.model.entity.Department;
import org.med.youhospital.serverside.model.entity.Hospital;
import org.med.youhospital.serverside.model.request.DepartmentReq;
import org.med.youhospital.serverside.model.response.DepartmentRes;
import org.med.youhospital.serverside.service.DepartmentService;
import org.med.youhospital.serverside.service.impl.DepartmentServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class DepartmentControllerTest {

    @Mock
    private DepartmentService departmentService = new DepartmentServiceImpl();

    @InjectMocks
    private DepartmentController departmentController;

    DepartmentRes department;

    DepartmentRes department1;

    DepartmentReq departmentReq;

    DepartmentRes departmentRes;

    List<DepartmentRes> departments;

    Hospital hospital;

    @BeforeEach
    public void setup(){
        hospital = new Hospital();
        hospital.setId(UUID.randomUUID());
        department = new DepartmentRes();
        department.setId(UUID.randomUUID());
        department.setName("Department");
        department1 = new DepartmentRes();
        department1.setId(UUID.randomUUID());
        department1.setName("Department 1");
        departments = Arrays.asList(department, department1);
        departmentRes = new DepartmentRes();
        departmentReq = new DepartmentReq();
        departmentReq.setHospitalId(hospital.getId());
    }

    @Test
    void testSave() {
        when(departmentService.save(departmentReq)).thenReturn(departmentRes);
        ResponseEntity<DepartmentRes> response = departmentController.save(departmentReq);
        verify(departmentService).save(departmentReq);
        assertEquals(departmentRes, response.getBody());
    }

    @Test
    void testFindOne() {
        when(departmentService.findOne(department.getId())).thenReturn(departmentRes);
        ResponseEntity<DepartmentRes> response = departmentController.findOne(department.getId());
        verify(departmentService).findOne(department.getId());
        assertEquals(departmentRes, response.getBody());
    }

    @Test
    public void testFindAll() {
        when(departmentService.findAll()).thenReturn(departments);
        ResponseEntity<List<DepartmentRes>> response = departmentController.findAll();
        verify(departmentService).findAll();
        assertEquals(departments, response.getBody());
    }

    @Test
    void testUpdate() {
        when(departmentService.update(department.getId(), departmentReq)).thenReturn(departmentRes);
        ResponseEntity<DepartmentRes> response = departmentController.update(department.getId(), departmentReq);
        verify(departmentService).update(department.getId(),departmentReq);
        assertEquals(departmentRes, response.getBody());
    }

    @Test
    void testDelete() {
        doNothing().when(departmentService).delete(department.getId());
        ResponseEntity<Void> response = departmentController.delete(department.getId());
        verify(departmentService).delete(department.getId());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
