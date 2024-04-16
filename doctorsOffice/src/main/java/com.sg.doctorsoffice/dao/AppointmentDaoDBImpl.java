package com.sg.doctorsoffice.dao;

import com.sg.doctorsoffice.dao.mappers.AppointmentMapper;
import com.sg.doctorsoffice.model.Appointment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Repository
public class AppointmentDaoDBImpl implements AppointmentDao {

    private final JdbcTemplate jdbc;

    public AppointmentDaoDBImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Appointment addNewAppointment(Appointment appointment) {
        final String INSERT_APPOINTMENT = "INSERT INTO appointment(date, description, Doctor_did, Patient_pid) VALUES (?, ?, ?, ?)";
        jdbc.update(INSERT_APPOINTMENT,
                Date.valueOf(appointment.getDate()),
                appointment.getDescription(),
                appointment.getDoctor_id(),
                appointment.getPatient_id());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        appointment.setAid(newId);

        final String ADD_DOCTOR_PATIENT = "INSERT INTO doctor_has_patient (Doctor_did, Patient_pid ) VALUES (?,?)";
        jdbc.update(ADD_DOCTOR_PATIENT,appointment.getDoctor_id(),appointment.getPatient_id());

        return appointment;
    }

    @Override
    public List<Appointment> getAllAppointments() {
        final String SELECT_ALL_APPOINTMENTS = "SELECT * FROM appointment";

        return jdbc.query(SELECT_ALL_APPOINTMENTS, new AppointmentMapper());
    }

    @Override
    public List<Appointment> getAppointmentsByDate(LocalDate date) {
        final String SELECT_APPOINTMENTS_BY_DATE = "SELECT * FROM appointment WHERE date = ?";

        return jdbc.query(SELECT_APPOINTMENTS_BY_DATE, new AppointmentMapper(), Date.valueOf(date));
    }

    @Override
    public Appointment getAppointmentById(int id) {
        final String SELECT_APPOINTMENT_BY_ID = "SELECT * FROM appointment WHERE aid = ?";

        return jdbc.queryForObject(SELECT_APPOINTMENT_BY_ID, new AppointmentMapper(), id);
    }

    @Override
    public Appointment updateAppointment(int id, Appointment appointment) {
        final String UPDATE_APPOINTMENT = "UPDATE appointment SET date = ?, description = ?, Doctor_did = ?, Patient_pid = ? WHERE aid = ?";

        jdbc.update(UPDATE_APPOINTMENT,
                Date.valueOf(appointment.getDate()),
                appointment.getDescription(),
                appointment.getDoctor_id(),
                appointment.getPatient_id(),
                appointment.getAid());

        return getAppointmentById(id);
    }

    @Override
    public void deleteAppointment(int id) {
        final String DELETE_APPOINTMENT = "DELETE FROM appointment WHERE aid = ?";
        jdbc.update(DELETE_APPOINTMENT, id);
    }

    @Override
    public Appointment checkIfDateEqualsBirthDate(int patient_id) {
        final String sql = "SELECT a.*\n" +
                "FROM appointment a\n" +
                "JOIN patient p ON a.patient_pid = p.pid\n" +
                "WHERE month(a.date) = month(p.birthdate)\n" +
                "AND day(a.date) = day(p.birthdate)\n" +
                "AND a.patient_pid = ?";

        return jdbc.queryForObject(sql, new AppointmentMapper(), patient_id);
    }

}
