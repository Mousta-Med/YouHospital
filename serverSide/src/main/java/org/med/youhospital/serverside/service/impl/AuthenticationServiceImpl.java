package org.med.youhospital.serverside.service.impl;


import org.med.youhospital.serverside.jwt.JWTUtil;
import org.med.youhospital.serverside.model.entity.Admin;
import org.med.youhospital.serverside.model.entity.Patient;
import org.med.youhospital.serverside.model.entity.Person;
import org.med.youhospital.serverside.model.entity.Staff;
import org.med.youhospital.serverside.model.request.AuthenticationReq;
import org.med.youhospital.serverside.model.request.PatientReq;
import org.med.youhospital.serverside.model.request.PersonReq;
import org.med.youhospital.serverside.model.request.StaffReq;
import org.med.youhospital.serverside.model.response.AdminRes;
import org.med.youhospital.serverside.model.response.AuthenticationRes;
import org.med.youhospital.serverside.model.response.PatientRes;
import org.med.youhospital.serverside.repository.AdminRepository;
import org.med.youhospital.serverside.repository.PatientRepository;
import org.med.youhospital.serverside.repository.StaffRepository;
import org.med.youhospital.serverside.service.AuthenticationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;


@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public AuthenticationRes register(PatientReq patientReq){
        Patient patient = modelMapper.map(patientReq, Patient.class);
        patient.setPass(passwordEncoder.encode(patientReq.getPass()));
        patient = patientRepository.save(patient);
        String token = jwtUtil.generateToken(patient, "PATIENT");
         return new AuthenticationRes(token, "PATIENT",Optional.empty(), Optional.empty(), Optional.of(patientReq));
    }

    @Override
    public AuthenticationRes login(AuthenticationReq authenticationReq) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationReq.getEmail(),
                        authenticationReq.getPassword()
                )
        );
        String token = null;
        Optional<Admin> admin = adminRepository.findByEmail(authenticationReq.getEmail());
        Optional<Patient> patient = patientRepository.findByEmail(authenticationReq.getEmail());
        Optional<Staff> staff = staffRepository.findByEmail(authenticationReq.getEmail());

        if (admin.isPresent()) {
            token = jwtUtil.generateToken(admin.get(),"ADMIN");
            return new AuthenticationRes(token, "ADMIN",Optional.empty(),Optional.of( modelMapper.map(admin, AdminRes.class)), Optional.empty());
        }
        if (patient.isPresent()) {
            token = jwtUtil.generateToken(patient.get(), "PATIENT");
            return new AuthenticationRes(token, "PATIENT",Optional.empty(), Optional.empty(), Optional.of(modelMapper.map(patient, PatientReq.class)));
        }
        if (staff.isPresent()) {
            token = jwtUtil.generateToken(staff.get(), staff.get().getRole().name());
            return new AuthenticationRes(token, staff.get().getRole().name(),Optional.of(modelMapper.map(staff, StaffReq.class)),Optional.empty(),Optional.empty());
        }
        throw new UsernameNotFoundException("User Not Found With This Email: " + authenticationReq.getEmail());
    }

//    @Override
//    public AuthenticationRes login(AuthenticationReq userDto) {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            userDto.getEmail(),
//                            userDto.getPassword()
//                    )
//            );
//        Optional<? extends Person> user = Stream.of(
//                        adminRepository.findByEmail(userDto.getEmail()),
//                        patientRepository.findByEmail(userDto.getEmail()),
//                        staffRepository.findByEmail(userDto.getEmail()))
//                .filter(Optional::isPresent)
//                .findFirst()
//                .orElseThrow(() -> new UsernameNotFoundException("User Not Found With This Email: " + userDto.getEmail()));
//
//        String token = jwtUtil.generateToken(user.get());
//        return new AuthenticationRes(token, "", user.get());
//    }


}
