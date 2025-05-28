package com.jgh.mcpserver.service;

import com.jgh.mcpserver.model.Doctor;
import com.jgh.mcpserver.repository.DoctorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {
  private static final Logger logger = LoggerFactory.getLogger(DoctorService.class);

  @Autowired private DoctorRepository doctorRepository;

  @Tool(
      name = "create_doctor",
      description =
          "Creates a doctor record, with the given first name, last name and medical specialization.")
  public Doctor createDoctor(
      @ToolParam(description = "first name") String firstName,
      @ToolParam(description = "last name") String lastName,
      @ToolParam(description = "medical specialization") String specialization) {

    Doctor doctor = new Doctor();
    doctor.setFirstName(firstName);
    doctor.setLastName(lastName);
    doctor.setSpecialization(specialization);

    logger.info(
        "Creating Doctor {} {}, with medical specialization: {}",
        firstName,
        lastName,
        specialization);
    return doctorRepository.save(doctor);
  }

  @Tool(name = "delete_doctor", description = "Deletes the doctor record for the given id.")
  public void deleteDoctor(@ToolParam(description = "id of the doctor") Long id) {
    logger.info("Deleting doctor with id {}", id);
    doctorRepository.deleteById(id);
  }
}
