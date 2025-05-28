package com.jgh.mcpserver.service;

import com.jgh.mcpserver.model.Patient;
import com.jgh.mcpserver.repository.PatientRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
  private static final Logger logger = LoggerFactory.getLogger(PatientService.class);

  @Autowired private PatientRepository patientRepository;

  @Tool(
      name = "list_patients",
      description =
          "Lists all patients, including id, first name, last name, email address and phone number.")
  public List<Patient> getAllPatients() {
    return patientRepository.findAll();
  }

  @Tool(name = "create_patient", description = "Create new patient record with human name")
  public Patient createPatient(
      @ToolParam(description = "first name") String firstName,
      @ToolParam(description = "last name") String lastName,
      @ToolParam(description = "email") String email,
      @ToolParam(description = "phone number") String phoneNumber) {
    Patient patient = new Patient();
    patient.setFirstName(firstName);
    patient.setLastName(lastName);
    patient.setEmail(email);
    patient.setPhone(phoneNumber);
    logger.info(
        "Creating patient {} {}, with email address: {}, phone number: {}",
        firstName,
        lastName,
        email,
        phoneNumber);
    return patientRepository.save(patient);
  }

  // @Transactional
  @Tool(
      name = "update_patient",
      description =
          "Update patient record for the patient with the given id. The first name, last name, email address, and phone number can be updated.")
  public Patient update(
      @ToolParam(description = "id") Long id,
      @ToolParam(description = "first name") String firstName,
      @ToolParam(description = "last name") String lastName,
      @ToolParam(description = "email") String email,
      @ToolParam(description = "phone number") String phoneNumber) {

    Optional<Patient> optionalUser = patientRepository.findById(id);
    if (optionalUser.isEmpty()) {
      // throw new EntityNotFoundException("Patient record not found with id: " + id);
      return new Patient();
    }
    Patient patient = optionalUser.get();
    patient.setId(id);
    if (firstName != null && !firstName.isEmpty()) patient.setFirstName(firstName);

    if (lastName != null && !lastName.isEmpty()) patient.setLastName(lastName);

    if (email != null && !email.isEmpty()) patient.setEmail(email);

    if (phoneNumber != null && !phoneNumber.isEmpty()) patient.setPhone(phoneNumber);

    logger.info(
        "Updating patient with id {}, Name: {} {}, with email address: {}, phone number: {}",
        id,
        firstName,
        lastName,
        email,
        phoneNumber);
    return patientRepository.save(patient);
  }

  @Tool(name = "get_patient_by_id", description = "Gets the patient record with the given id")
  public Patient getPatientById(@ToolParam(description = "id of the patient") Long id) {

    logger.info("Getting patient by id {}", id);
    return patientRepository.findById(id).orElse(null);
  }

  @Tool(name = "delete_patient", description = "Deletes the patient record for the given id.")
  public void deletePatient(@ToolParam(description = "id of the patient") Long id) {
    logger.info("Deleting patient with id {}", id);
    patientRepository.deleteById(id);
  }
}
