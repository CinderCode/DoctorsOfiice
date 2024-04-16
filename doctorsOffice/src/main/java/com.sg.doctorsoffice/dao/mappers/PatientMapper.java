package com.sg.doctorsoffice.dao.mappers;

import com.sg.doctorsoffice.model.Patient;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class PatientMapper implements RowMapper<Patient> {
    @Override
    public Patient mapRow(ResultSet rs, int rowNum) throws SQLException {

        Patient patient = new Patient();
        patient.setPid(rs.getInt("pid"));
        patient.setpFName(rs.getString("pFname"));
        patient.setpLName(rs.getString("pLname"));
        patient.setPhone(rs.getString("phone"));
        patient.setBirthDate(rs.getDate("birthdate").toLocalDate());
        patient.setMedicalHistory(rs.getString("medicalHistory"));
        patient.setInsurance(rs.getString("insurance"));
        return patient;

    }
}
