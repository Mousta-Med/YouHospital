package org.med.youhospital.serverside.repository;

import org.med.youhospital.serverside.model.entity.Examination;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExaminationRepository extends JpaRepository<Examination, UUID> {
}