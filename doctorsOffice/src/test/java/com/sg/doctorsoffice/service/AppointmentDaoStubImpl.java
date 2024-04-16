package com.sg.doctorsoffice.service;

import com.sg.doctorsoffice.dao.AppointmentDao;
import com.sg.doctorsoffice.model.Appointment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDaoStubImpl implements AppointmentDao {

    public Appointment onlyAppointment;

    public AppointmentDaoStubImpl() {
        onlyAppointment = new Appointment();
        onlyAppointment.setAid(1);
        onlyAppointment.setDate(LocalDate.of(2024, 2, 29));
        onlyAppointment.setDoctor_id(2);
        onlyAppointment.setPatient_id(3);
        onlyAppointment.setDescription("Leg broken");
    }

    public AppointmentDaoStubImpl(Appointment appointment) {
        onlyAppointment = appointment;
    }

    @Override
    public Appointment addNewAppointment(Appointment appointment) {
        if (appointment.equals(onlyAppointment)) {
            return onlyAppointment;
        } else {
            return null;
        }
    }

    @Override
    public List<Appointment> getAllAppointments() {
        List<Appointment> appointmentList = new ArrayList<>();
        appointmentList.add(onlyAppointment);

        return appointmentList;
    }

    @Override
    public List<Appointment> getAppointmentsByDate(LocalDate date) {
        List<Appointment> appointmentList = new ArrayList<>();

        if (date.equals(onlyAppointment.getDate())) {
            appointmentList.add(onlyAppointment);
        }

        return appointmentList;
    }

    @Override
    public Appointment getAppointmentById(int id) {
        if (onlyAppointment.getAid() == id) {
            return onlyAppointment;
        } else {
            return null;
        }
    }

    @Override
    public Appointment updateAppointment(int id, Appointment appointment) {
        return null;
    }

    @Override
    public void deleteAppointment(int id) {

    }

    @Override
    public Appointment checkIfDateEqualsBirthDate(int patient_id) {
        return null;
    }
}
