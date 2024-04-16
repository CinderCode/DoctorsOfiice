package com.sg.doctorsoffice.service;

import com.sg.doctorsoffice.dao.DoctorDao;
import com.sg.doctorsoffice.dao.PatientDao;
import com.sg.doctorsoffice.model.Appointment;
import com.sg.doctorsoffice.model.Doctor;
import com.sg.doctorsoffice.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService{
    @Autowired
    PatientDao patientDao;
    @Autowired
    DoctorDao doctorDao;

    @Override
    public Patient getPatientById(int id) {
        return patientDao.getPatientById(id);
    }

    @Override
    public Patient addNewPatient(Patient patient) {
        if(!validatePatientData(patient)){
            return null;
        }

        return patientDao.createNewPatient(patient);
    }

    @Override
    public Patient deletePatientById(int id) {
        Patient deletedPatient = patientDao.getPatientById(id);

        if (deletedPatient == null) {
            return null;
        }
        patientDao.deletePatient(id);
        return deletedPatient;

    }

    @Override
    public List<Patient> getAllPatients() {
        return patientDao.getAllPatients();
    }

    @Override
    public Patient updatePatient(int id, Patient patient) {
        if(id == patient.getPid()){
            if(!validatePatientData(patient)){
                return null;
            }
            return patientDao.updatePatient(id, patient);
        }else{
            return null;
        }
    }

    @Override
    public List<Appointment> getAppointmentsByPatient(int id) {
        if(patientDao.getPatientById(id) == null){
            return null;
        }
        return patientDao.getAppointmentsByPatient(id);
    }

    @Override
    public boolean insertPatientToDoctor(int pid, int doctorId){
        if(patientDao.getPatientById(pid) == null || doctorDao.findDoctorById(doctorId) == null){
            return false;
        }
        patientDao.addPatientToDoctor(pid, doctorId);
        return true;
    }

    @Override
    public boolean deletePatientFromDoctor(int pid, int doctorId){
        if(patientDao.getPatientById(pid) == null || doctorDao.findDoctorById(doctorId) == null){
            return false;
        }
        patientDao.deletePatientFromDoctor(pid, doctorId);
        return true;

    }

    private boolean validatePatientData(Patient patient){
        // Only medical history and insurance can be null
        if(patient.getpFName() == null || patient.getpFName().isBlank()){
            return false;
        }
        if(patient.getpLName() == null || patient.getpLName().isBlank()){
            return false;
        }
        if(patient.getBirthDate() == null){
            return false;
        }

        if(patient.getPhone() == null || patient.getPhone().isBlank()){
            return false;
        }

        // phone number has to be in the right format
        if(!patient.getPhone().matches("\\d{3}-\\d{3}-\\d{4}")){
            return false;
        }

        // Date has to be in the past
        if(patient.getBirthDate().isAfter(LocalDate.now())){
            return false;
        }
        return true;
    }
}
