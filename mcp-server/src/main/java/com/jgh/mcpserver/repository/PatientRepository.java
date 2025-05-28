package com.jgh.mcpserver.repository;

import com.jgh.mcpserver.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
  Patient findByEmail(String email);
}
