package com.sg.doctorsoffice.service;

import com.sg.doctorsoffice.dao.AppointmentDao;
import com.sg.doctorsoffice.dao.DoctorDao;
import com.sg.doctorsoffice.dao.PatientDao;
import com.sg.doctorsoffice.model.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
@Service
public class AppointmentServiceImpl implements AppointmentService{

    @Autowired
    AppointmentDao appointmentDao;

    @Autowired
    DoctorDao doctorDao;

    @Autowired
    PatientDao patientDao;
    @Override
    public Appointment getAppointmentById(int id) {
        return appointmentDao.getAppointmentById(id);
    }

    @Override
    public Appointment updateAppointment(int id, Appointment appointment) {
        if(id == appointment.getAid()){
            if(!validateAppointmentData(appointment)){
                return null;
            }
            appointmentDao.updateAppointment(id,appointment);
        }else{
            return null;
        }
        return appointmentDao.getAppointmentById(appointment.getAid());
    }

    @Override
    public Appointment deleteAppointment(int id) {
        Appointment deletedAppointment = appointmentDao.getAppointmentById(id);

        if(deletedAppointment==null){
            return null;
        }
        appointmentDao.deleteAppointment(id);

        return deletedAppointment;
    }

    @Override
    public Appointment checkIfDateEqualsBirthDate(int id) {
        if(patientDao.getPatientById(id) == null){
            return null;
        }
        Appointment appointment = appointmentDao.checkIfDateEqualsBirthDate(id);
        appointment.setDescription(appointment.getDescription() + " happy birthday");
        return appointment;
    }

    @Override
    public Appointment addNewAppointment(Appointment appointment) {
        if(!validateAppointmentData(appointment)){
            return null;
        }
        return appointmentDao.addNewAppointment(appointment);
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentDao.getAllAppointments();
    }

    @Override
    public List<Appointment> getAppointmentsByDate(String dateAsString) {

        LocalDate date = null;

        if(dateAsString.isBlank() || dateAsString.isEmpty()) {
            return null;
        }
        try {
            date = LocalDate.parse(dateAsString);
        } catch (DateTimeParseException ex) {
            return null;
        }

        return appointmentDao.getAppointmentsByDate(date);
    }

    public boolean validateAppointmentData(Appointment appointment){

            // Only description can be null
        if(appointment.getDate()==null){
            return false;
        }
        if(doctorDao.findDoctorById(appointment.getDoctor_id())==null){
            return false;
        }
        if(patientDao.getPatientById(appointment.getPatient_id())==null){
            return false;
        }

        if(appointment.getDate().isBefore(LocalDate.now())){
            return false;
        }

        return true;
    }



}
