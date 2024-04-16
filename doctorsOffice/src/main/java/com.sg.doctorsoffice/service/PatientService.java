package com.sg.doctorsoffice.service;

import com.sg.doctorsoffice.model.Appointment;
import com.sg.doctorsoffice.model.Patient;

import java.util.List;

public interface PatientService {
    Patient getPatientById(int id);
    Patient addNewPatient(Patient patient);
    Patient deletePatientById(int id);

    List<Patient> getAllPatients();

    Patient updatePatient(int id, Patient patient);

    boolean insertPatientToDoctor(int pid, int doctorId);
    boolean deletePatientFromDoctor(int pid, int doctorId);

    List<Appointment> getAppointmentsByPatient(int id);
}