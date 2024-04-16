package com.sg.doctorsoffice.dao;

import com.sg.doctorsoffice.dao.mappers.AppointmentMapper;
import com.sg.doctorsoffice.dao.mappers.PatientMapper;
import com.sg.doctorsoffice.model.Appointment;
import com.sg.doctorsoffice.model.Patient;
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
public class PatientDaoDBImpl implements PatientDao {

    @Autowired
    JdbcTemplate jdbc;
    @Override
    @Transactional
    public Patient createNewPatient(Patient patient) {
        final String sql = "INSERT INTO patient (pFName, pLName, phone, birthdate, medicalHistory, insurance) " +
                "VALUES (?,?,?,?,?,?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, patient.getpFName());
            statement.setString(2, patient.getpLName());
            statement.setString(3, patient.getPhone());
            statement.setObject(4, java.sql.Date.valueOf(patient.getBirthDate()));
            statement.setString(5, patient.getMedicalHistory());
            statement.setString(6, patient.getInsurance());
            return statement;

        }, keyHolder);

        patient.setPid(keyHolder.getKey().intValue());
        return patient;
    }

    @Override
    public List<Patient> getAllPatients() {
        final String sql = "SELECT * FROM patient";
        return jdbc.query(sql, new PatientMapper());
    }

    @Override
    public Patient getPatientById(int id) {
        final String sql = "SELECT * FROM patient WHERE pid=?";
        return jdbc.queryForObject(sql, new PatientMapper(), id);
    }

    @Override
    @Transactional
    public Patient updatePatient(int id, Patient patient) {
        final String sql = "UPDATE patient " +
                "SET pFName=?, pLName=?, phone=?, birthDate=?, medicalHistory=?, insurance=? " +
                "WHERE pid=?";

        jdbc.update(sql,
                patient.getpFName(),
                patient.getpLName(),
                patient.getPhone(),
                patient.getBirthDate(),
                patient.getMedicalHistory(),
                patient.getInsurance(),
                id);

        return getPatientById(id);
    }


    @Override
    public void deletePatient(int id) {
        final String DELETE_APPOINTMENT_WITH_PATIENT = "DELETE FROM appointment WHERE patient_pid = ?";
        jdbc.update(DELETE_APPOINTMENT_WITH_PATIENT, id);

        final String DELETE_PATIENT_DOCTOR = "DELETE FROM doctor_has_patient WHERE patient_pid = ?";
        jdbc.update(DELETE_PATIENT_DOCTOR, id);

        final String DELETE_PATIENT = "DELETE FROM patient WHERE pid = ?";
        jdbc.update(DELETE_PATIENT, id);
    }

    @Override
    public List<Appointment> getAppointmentsByPatient(int id) {
        final String sql = "SELECT * FROM appointment " +
                "WHERE Patient_pid = ?";
        return jdbc.query(sql, new AppointmentMapper(), id);
    }

    @Override
    public void addPatientToDoctor(int patient_id, int doctor_id) {
        final String sql = "INSERT INTO doctor_has_patient(patient_pid, doctor_did) VALUES(?,?)";
        jdbc.update(sql, patient_id, doctor_id);
    }

    @Override
    public void deletePatientFromDoctor(int patient_id, int doctor_id) {
        final String sql = "DELETE FROM doctor_has_patient WHERE patient_pid = ? AND doctor_did = ?";
        jdbc.update(sql, patient_id, doctor_id);
    }
}
