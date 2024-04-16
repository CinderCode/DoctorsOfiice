package com.sg.doctorsoffice.dao.mappers;

import com.sg.doctorsoffice.model.Doctor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DoctorMapper implements RowMapper<Doctor> {
    @Override
    public Doctor mapRow(ResultSet rs, int rowNum) throws SQLException {

        Doctor doctor = new Doctor();
        doctor.setDid(rs.getInt("did"));
        doctor.setdFName(rs.getString("fname"));
        doctor.setdLName(rs.getString("lname"));
        doctor.setType(rs.getString("type"));
        return doctor;

    }
}
