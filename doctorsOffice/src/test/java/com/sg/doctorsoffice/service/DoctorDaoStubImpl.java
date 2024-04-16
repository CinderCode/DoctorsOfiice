package com.sg.doctorsoffice.service;

import com.sg.doctorsoffice.dao.DoctorDao;
import com.sg.doctorsoffice.model.Appointment;
import com.sg.doctorsoffice.model.Doctor;
import com.sg.doctorsoffice.model.DoctorAppointment;
import com.sg.doctorsoffice.model.Patient;

import java.util.List;

public class DoctorDaoStubImpl implements DoctorDao {


    public Doctor onlyDoctor;

    public DoctorDaoStubImpl(){
        onlyDoctor = new Doctor();
        onlyDoctor.setDid(1);
        onlyDoctor.setdFName("Test First");
        onlyDoctor.setdLName("Test Last");
        onlyDoctor.setType("Test Type");
    }


    @Override
    public Doctor createNewDoctor(Doctor doctor) {
        if(doctor.equals(onlyDoctor)){
            return onlyDoctor;
        }
        return null;
    }

    @Override
    public List<Doctor> getAllDoctors() {
        // pass through method no tests
        return null;
    }

    @Override
    public Doctor findDoctorById(int id) {
        if(id == onlyDoctor.getDid()){
            return onlyDoctor;
        }
        return null;
    }

    @Override
    public Doctor updateDoctor(int id, Doctor doctor) {
        return onlyDoctor;
    }

    @Override
    public void deleteDoctor(int id) {

    }

    @Override
    public DoctorAppointment getNumberOfAppointmentsForDoctor(int id) {
        return null;
    }

    @Override
    public List<Appointment> getAppointmentByMonthForDoctor(int month, int year, int id) {
        return null;
    }

    @Override
    public List<Patient> getPatientsByDoctor(int id) {
        return null;
    }

    @Override
    public List<Appointment> getAppointmentsByDoctor(int id) {
        return null;
    }
}
