package com.sg.doctorsoffice.service;

import com.sg.doctorsoffice.dao.DoctorDao;
import com.sg.doctorsoffice.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    DoctorDao doctorDao;

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorDao.getAllDoctors();
    }

    @Override
    public Doctor findDoctorById(int id) {
        return doctorDao.findDoctorById(id);
    }

    @Override
    public Doctor createNewDoctor(Doctor doctor) {
        // check if first name and type are empty
        if (!validateDoctorData(doctor)) {
            return null;
        }

        return doctorDao.createNewDoctor(doctor);
    }

    @Override
    public Doctor updateDoctor(int id, Doctor doctor) {
        if (id != doctor.getDid()) {
            return null;
        }

        // check if first name and type are empty
        if (!validateDoctorData(doctor)) {
            // first name or type is empty, return null
            return null;
        }

        doctorDao.updateDoctor(id, doctor);
        return doctorDao.findDoctorById(doctor.getDid());
    }

    @Override
    public Doctor deleteDoctorById(int id) {

        Doctor deletedDoctor = doctorDao.findDoctorById(id);

        if (deletedDoctor == null) {
            return null;
        }
        doctorDao.deleteDoctor(id);
        return deletedDoctor;

    }

    @Override
    public List<Appointment> getAppointmentsByDoctor(int id) {
        if (doctorDao.findDoctorById(id) == null) {
            return null;
        }
        return doctorDao.getAppointmentsByDoctor(id);
    }

    @Override
    public DoctorAppointment getTotalAppointmentsByDoctor(int id) {
        if (doctorDao.findDoctorById(id) == null) {
            return null;
        }
        return doctorDao.getNumberOfAppointmentsForDoctor(id);
    }

    @Override
    public List<Appointment> getTotalAppointmentsByMonth(int month, int year, int id) {
        if (!validateMonth(month) || doctorDao.findDoctorById(id) == null) {
            return null;
        }
        return doctorDao.getAppointmentByMonthForDoctor(month, year, id);
    }

    @Override
    public List<Patient> getPatientsByDoctor(int id) {
        if (doctorDao.findDoctorById(id) == null) {
            return null;
        }
        return doctorDao.getPatientsByDoctor(id);
    }

    private boolean validateDoctorData(Doctor doctor) {
        return doctor.getdFName() != null && !doctor.getdFName().isEmpty() &&
                doctor.getType() != null && !doctor.getType().isEmpty();
    }

    private boolean validateMonth(int month) {
        return month >= 1 && month <= 12;
    }

}
