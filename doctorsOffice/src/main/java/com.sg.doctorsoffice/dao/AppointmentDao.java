package com.sg.doctorsoffice.dao;

import com.sg.doctorsoffice.App;
import com.sg.doctorsoffice.model.Appointment;
import com.sg.doctorsoffice.model.Patient;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentDao {

    Appointment addNewAppointment(Appointment appointment);

    List<Appointment> getAllAppointments();

    List<Appointment> getAppointmentsByDate(LocalDate date);

    Appointment getAppointmentById(int id);

    Appointment updateAppointment(int id, Appointment appointment);

    void deleteAppointment(int id);

    Appointment checkIfDateEqualsBirthDate(int patient_id);

}
