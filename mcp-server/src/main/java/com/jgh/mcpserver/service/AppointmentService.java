package com.jgh.mcpserver.service;

import com.jgh.mcpserver.model.Appointment;
import com.jgh.mcpserver.model.Doctor;
import com.jgh.mcpserver.model.Patient;
import com.jgh.mcpserver.repository.AppointmentRepository;
import com.jgh.mcpserver.repository.DoctorRepository;
import com.jgh.mcpserver.repository.PatientRepository;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {
  private static final Logger logger = LoggerFactory.getLogger(AppointmentService.class);

  @Autowired private AppointmentRepository appointmentRepository;

  @Autowired private DoctorRepository doctorRepository;

  @Autowired private PatientRepository patientRepository;

  @Tool(
      name = "create_appointment",
      description =
          "Creates an appointment based on the given doctor id, patient id and date/time of the appointment.")
  public Appointment createAppointment(
      @ToolParam(description = "id of the doctor") Long doctorId,
      @ToolParam(description = "id of the patient") Long patientId,
      @ToolParam(description = "time and date of the appointment, in format YYYY-MM-DDThh:mm")
          String appointmentTime) {
    Doctor doctor =
        doctorRepository
            .findById(doctorId)
            .orElseThrow(
                () -> new IllegalArgumentException("Doctor not found with id " + doctorId));

    Patient patient =
        patientRepository
            .findById(patientId)
            .orElseThrow(
                () -> new IllegalArgumentException("Patient not found with id " + patientId));

    Appointment appointment = new Appointment();
    appointment.setDoctor(doctor);
    appointment.setPatient(patient);
    appointment.setAppointmentTime(LocalDateTime.parse(appointmentTime));
    logger.info(
        "Creating an appointment with doctor with id {}, patient with id {}, at time {}",
        doctorId,
        patientId,
        appointmentTime);
    return appointmentRepository.save(appointment);
  }

  @Tool(
      name = "delete_appointment",
      description = "Deletes an appointment based on the given appointment id.")
  public boolean deleteAppointment(
      @ToolParam(description = "id of the appointment") Long appointmentId) {
    logger.info("Deleting an appointment with id {}", appointmentId);
    appointmentRepository.deleteById(appointmentId);
    return true;
  }
}
