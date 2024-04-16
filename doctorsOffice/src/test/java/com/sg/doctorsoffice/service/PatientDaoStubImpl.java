package com.sg.doctorsoffice.service;

import com.sg.doctorsoffice.dao.PatientDao;
import com.sg.doctorsoffice.model.Appointment;
import com.sg.doctorsoffice.model.Patient;

import java.time.LocalDate;
import java.util.List;

public class PatientDaoStubImpl implements PatientDao {

    public Patient onlyPatient;

    public PatientDaoStubImpl(){

        onlyPatient = new Patient();
        onlyPatient.setPid(1);
        onlyPatient.setpFName("Test pFName");
        onlyPatient.setpLName("Test pLName");
        onlyPatient.setPhone("773-876-0912");
        onlyPatient.setBirthDate(LocalDate.of(1998,9,26));
        onlyPatient.setMedicalHistory("Brain Surgery");
        onlyPatient.setInsurance("Aetna");
    }
    @Override
    public Patient createNewPatient(Patient patient){

        if(patient.equals(onlyPatient)){
            return onlyPatient;
        }
        return null;
    }
    @Override
    public List<Patient> getAllPatients(){

        return null;
    }



    @Override
    public Patient getPatientById(int id){
        if(id==onlyPatient.getPid()){
            return onlyPatient;
        }
        return null;
    }

    @Override
    public Patient updatePatient(int id, Patient patient){
        return onlyPatient;

    }

    @Override
    public void deletePatient(int id){

    }

    @Override
    public void addPatientToDoctor(int pid, int did){

    }

    @Override
    public  void deletePatientFromDoctor(int pid, int doctorId){

    }
    @Override
    public List<Appointment> getAppointmentsByPatient(int id){
        return null;
    }




}
