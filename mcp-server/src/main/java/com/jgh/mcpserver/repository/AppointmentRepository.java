package com.jgh.mcpserver.repository;

import com.jgh.mcpserver.model.Appointment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

  List<Appointment> findByDoctorId(Long doctorId);

  List<Appointment> findByPatientId(Long patientId);
}
