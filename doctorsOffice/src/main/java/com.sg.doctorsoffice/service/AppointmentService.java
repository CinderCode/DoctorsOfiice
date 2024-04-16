package com.sg.doctorsoffice.service;

import com.sg.doctorsoffice.App;
import com.sg.doctorsoffice.model.Appointment;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentService {

    Appointment addNewAppointment(Appointment appointment);

    List<Appointment> getAllAppointments();

    List<Appointment> getAppointmentsByDate(String dateAsString);

    Appointment getAppointmentById(int id);

    Appointment updateAppointment(int id, Appointment appointment);

    Appointment deleteAppointment(int id);

    Appointment checkIfDateEqualsBirthDate( int id );
}