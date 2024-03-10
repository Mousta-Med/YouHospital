package org.med.youhospital.serverside.service.impl;

import org.med.youhospital.serverside.model.entity.Admin;
import org.med.youhospital.serverside.model.entity.Patient;
import org.med.youhospital.serverside.model.entity.Staff;
import org.med.youhospital.serverside.repository.AdminRepository;
import org.med.youhospital.serverside.repository.PatientRepository;
import org.med.youhospital.serverside.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Admin> adminUser = adminRepository.findByEmail(username);
        if (adminUser.isPresent()) {
            return adminRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("No User Found with This given UserName"));
        }

        Optional<Staff> staffUser = staffRepository.findByEmail(username);
        if (staffUser.isPresent()) {
            return staffRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("No User Found with This given UserName"));
        }

        Optional<Patient> patientUser = patientRepository.findByEmail(username);
        if (patientUser.isPresent()) {
            return patientRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("No User Found with This given UserName"));
        }

        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
