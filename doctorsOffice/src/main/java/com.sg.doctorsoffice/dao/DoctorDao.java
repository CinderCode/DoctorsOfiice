package com.sg.doctorsoffice.dao;

import com.sg.doctorsoffice.model.*;


import java.util.List;

public interface DoctorDao {

    Doctor createNewDoctor(Doctor doctor);

    List<Doctor> getAllDoctors();

    Doctor findDoctorById(int id);

    Doctor updateDoctor(int id, Doctor doctor);
    void deleteDoctor(int id);

    DoctorAppointment getNumberOfAppointmentsForDoctor(int id);

    List<Appointment> getAppointmentByMonthForDoctor(int month, int year, int id);
    List<Patient>getPatientsByDoctor(int id);

    List<Appointment> getAppointmentsByDoctor(int id);


}
