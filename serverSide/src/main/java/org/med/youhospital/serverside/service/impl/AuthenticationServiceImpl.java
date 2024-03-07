package org.med.youhospital.serverside.service.impl;


import org.med.youhospital.serverside.jwt.JWTUtil;
import org.med.youhospital.serverside.model.entity.Admin;
import org.med.youhospital.serverside.model.entity.Patient;
import org.med.youhospital.serverside.model.entity.Staff;
import org.med.youhospital.serverside.model.request.AuthenticationReq;
import org.med.youhospital.serverside.model.response.AuthenticationResponse;
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

//    @Override
//    public AuthenticationResponse register(UserDto userDto){
//        User user = modelMapper.map(userDto, User.class);
//        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
//        user.setStatus(Status.PENDING);
//        user = userRepository.save(user);
//        String token = jwtUtil.generateToken(user);
//         return new AuthenticationResponse(token, modelMapper.map(user, UserRespDto.class));
//    }

    @Override
    public AuthenticationResponse login(AuthenticationReq authenticationReq) {
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
            token = jwtUtil.generateToken(admin.get());
            return new AuthenticationResponse(token);
        }
        if (patient.isPresent()) {
            token = jwtUtil.generateToken(patient.get());
            return new AuthenticationResponse(token);
        }
        if (staff.isPresent()) {
            token = jwtUtil.generateToken(staff.get());
            return new AuthenticationResponse(token);
        }
        throw new UsernameNotFoundException("User Not Found With This Email: " + authenticationReq.getEmail());
    }

//    @Override
//    public AuthenticationResponse login(AuthenticationRequest userDto) {
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
//        return new AuthenticationResponse(token);
//    }


}
