package com.sg.doctorsoffice.dao;

import com.sg.doctorsoffice.model.Doctor;
import com.sg.doctorsoffice.model.Patient;
import com.sg.doctorsoffice.model.Appointment;

import java.util.List;

public interface PatientDao {
    Patient createNewPatient(Patient patient);

    List<Patient> getAllPatients();

    Patient getPatientById(int id);

    Patient updatePatient(int id, Patient patient);
    void deletePatient(int id);

    List<Appointment> getAppointmentsByPatient(int id);

    void addPatientToDoctor(int patient_id, int doctor_id);

    void deletePatientFromDoctor(int patient, int doctor_id);


}
