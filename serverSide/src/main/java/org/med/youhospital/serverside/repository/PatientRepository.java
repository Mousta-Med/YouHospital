package org.med.youhospital.serverside.repository;

import org.med.youhospital.serverside.model.entity.Examination;
import org.med.youhospital.serverside.model.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {
    Optional<Patient> findByEmail(String email);

}