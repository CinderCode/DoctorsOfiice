package com.sg.doctorsoffice.dao.mappers;

import com.sg.doctorsoffice.model.DoctorAppointment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DoctorAppointmentMapper implements RowMapper<DoctorAppointment> {


    @Override
    public DoctorAppointment mapRow(ResultSet rs, int i) throws SQLException {

        DoctorAppointment dA = new DoctorAppointment();

        dA.setDoctorId(rs.getInt("Doctor_ID"));
        dA.setNumOfAppointments(rs.getInt("NumberOfAppointments"));

        return dA;
    }
}
