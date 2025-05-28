CREATE SCHEMA IF NOT EXISTS hospital;

CREATE TABLE IF NOT EXISTS hospital.patients (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    email VARCHAR(255),
    phone VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS hospital.doctors (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    specialization VARCHAR(255)
);

CREATE TABLE hospital.appointments (
    id SERIAL PRIMARY KEY,
    appointment_time TIMESTAMP NOT NULL,
    doctor_id BIGINT NOT NULL,
    patient_id BIGINT NOT NULL,

    CONSTRAINT fk_appointments_doctor
        FOREIGN KEY (doctor_id)
        REFERENCES hospital.doctors(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_appointments_patient
        FOREIGN KEY (patient_id)
        REFERENCES hospital.patients(id)
        ON DELETE CASCADE
);