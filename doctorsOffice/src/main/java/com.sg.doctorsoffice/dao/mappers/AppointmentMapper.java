package com.sg.doctorsoffice.dao.mappers;

import com.sg.doctorsoffice.model.Appointment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AppointmentMapper implements RowMapper<Appointment> {
    @Override
    public Appointment mapRow(ResultSet rs, int rowNum) throws SQLException {

        Appointment appointment = new Appointment();
        appointment.setAid(rs.getInt("aid"));
        appointment.setDate(rs.getDate("date").toLocalDate());
        appointment.setDescription(rs.getString("description"));
        appointment.setDoctor_id(rs.getInt("Doctor_did"));
        appointment.setPatient_id(rs.getInt("Patient_pid"));

        return appointment;

    }
}
