package com.sg.doctorsoffice.service;

import com.sg.doctorsoffice.model.*;

import javax.print.Doc;
import java.util.List;

public interface DoctorService {

    List<Doctor> getAllDoctors();

    Doctor findDoctorById(int id);

    Doctor createNewDoctor(Doctor doctor);

    Doctor updateDoctor(int id, Doctor doctor);

    Doctor deleteDoctorById(int id);

    List<Appointment> getAppointmentsByDoctor(int id);

    DoctorAppointment getTotalAppointmentsByDoctor(int id);

   List<Appointment> getTotalAppointmentsByMonth(int month, int year, int id);

    List<Patient> getPatientsByDoctor(int id);
}