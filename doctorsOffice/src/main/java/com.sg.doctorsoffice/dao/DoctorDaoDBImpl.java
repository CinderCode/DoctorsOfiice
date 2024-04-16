package com.sg.doctorsoffice.dao;

import com.sg.doctorsoffice.dao.mappers.*;
import com.sg.doctorsoffice.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
@Repository
public class DoctorDaoDBImpl implements DoctorDao {

    @Autowired
    private final JdbcTemplate jdbc;

    public DoctorDaoDBImpl(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }
    @Override
    @Transactional
    public Doctor createNewDoctor(Doctor doctor) {
        final String INSERT_DOCTOR = "INSERT INTO doctor (fName, lName, type) VALUES (?,?,?) ";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                    INSERT_DOCTOR,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, doctor.getdFName());
            statement.setString(2, doctor.getdLName());
            statement.setString(3, doctor.getType());
            return statement;

        }, keyHolder);

        doctor.setDid(keyHolder.getKey().intValue());
        return doctor;
    }

    @Override
    public List<Doctor> getAllDoctors() {

        final String SELECT_ALL_DOCTORS = "SELECT * FROM doctor";
        return jdbc.query(SELECT_ALL_DOCTORS, new DoctorMapper());
    }

    @Override
    public Doctor findDoctorById(int id) {

        final String SELECT_DOCTOR_BY_ID = "SELECT * FROM doctor WHERE did = ?";
        return jdbc.queryForObject(SELECT_DOCTOR_BY_ID,new DoctorMapper(),id);
    }

    @Override
    public Doctor updateDoctor(int id, Doctor doctor) {

        final String UPDATE_DOCTOR = "UPDATE Doctor SET fName = ?, lName = ?, type = ? WHERE did = ?";
        jdbc.update(UPDATE_DOCTOR,doctor.getdFName(),doctor.getdLName(),doctor.getType(),id);
        return findDoctorById(id);
    }

    @Override
    @Transactional
    public void deleteDoctor(int id) {

        final String DELETE_DOCTOR_FROM_APPOINTMENT = "DELETE FROM appointment WHERE Doctor_did = ? ";
        jdbc.update(DELETE_DOCTOR_FROM_APPOINTMENT,id);

        final String DELETE_DOCTOR_FROM_BRIDGE = "DELETE FROM doctor_has_patient WHERE Doctor_did = ?";
        jdbc.update(DELETE_DOCTOR_FROM_BRIDGE,id);

        final String DELETE_FROM_DOCTOR = "DELETE FROM doctor WHERE did = ?";
        jdbc.update(DELETE_FROM_DOCTOR,id);

    }
    @Override
    public List<Appointment>getAppointmentsByDoctor(int id){

        final String SELECT_APPOINTMENTS_BY_DOCTOR = "SELECT * FROM appointment " +
                "JOIN doctor ON appointment.Doctor_did = doctor.did " +
                "WHERE doctor_did = ?";

        return jdbc.query(SELECT_APPOINTMENTS_BY_DOCTOR, new AppointmentMapper(),id);


    }
    @Override
    public DoctorAppointment getNumberOfAppointmentsForDoctor(int id){

        final String SELECT_NUMBER_OF_APPOINTMENTS_PER_DOCTOR =
                "SELECT doctor.did as Doctor_ID , COUNT(appointment.aid) as NumberOfAppointments " +
                "FROM doctor " +
                "LEFT JOIN appointment ON doctor.did = appointment.Doctor_did " +
                "WHERE did = ?";


        return jdbc.queryForObject(SELECT_NUMBER_OF_APPOINTMENTS_PER_DOCTOR, new DoctorAppointmentMapper(),id);

    }

    @Override
    public List<Appointment> getAppointmentByMonthForDoctor(int month, int year, int id) {

        final String SELECT_APPOINTMENTS_PER_MONTH_FOR_DOCTOR =
                "SELECT aid, date, description, Doctor_did, Patient_pid " +
                "FROM appointment " +
                "JOIN doctor ON appointment.Doctor_did = doctor.did " +
                "WHERE month(date) = ? AND year (date) = ? AND doctor.did= ?";

        return jdbc.query(SELECT_APPOINTMENTS_PER_MONTH_FOR_DOCTOR, new AppointmentMapper(), month,year,id);
    }
    @Override
    public List<Patient>getPatientsByDoctor(int id){

        final String GET_PATIENTS_BY_DOCTOR = " SELECT * FROM patient " +
                "JOIN doctor_has_patient ON patient.pid = doctor_has_patient.Patient_pid " +
                "WHERE doctor_has_patient.Doctor_did = ?";

        return jdbc.query(GET_PATIENTS_BY_DOCTOR,new PatientMapper(),id);


    }

}
