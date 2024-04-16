package com.sg.doctorsoffice.dao;

import com.sg.doctorsoffice.App;
import com.sg.doctorsoffice.model.Doctor;
import com.sg.doctorsoffice.model.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = App.class)
class DoctorDaoImplTest {

    @Autowired
    private DoctorDao doctorDao;

    @Autowired
    private PatientDao patientDao;

    @BeforeEach
    public void setUp() {
        List<Doctor> doctors = doctorDao.getAllDoctors();
        for(Doctor doctor : doctors){
            doctorDao.deleteDoctor(doctor.getDid());
        }

        List<Patient> patients = patientDao.getAllPatients();
        for(Patient patient : patients){
            patientDao.deletePatient(patient.getPid());
        }

    }

    @Test
    public void testAddDoctor(){
        Doctor doctor = new Doctor();
        doctor.setdFName("Test First");
        doctor.setdLName("Test Last");
        doctor.setType("Test type");
        doctor = doctorDao.createNewDoctor(doctor);

        Doctor fromDao = doctorDao.findDoctorById(doctor.getDid());
        assertEquals(doctor, fromDao);
    }

    @Test
    public void testGetDoctor(){
        Doctor doctor = new Doctor();
        doctor.setdFName("Test First");
        doctor.setdLName("Test Last");
        doctor.setType("Test type");
        doctor = doctorDao.createNewDoctor(doctor);

        Doctor doctor2 = new Doctor();
        doctor2.setdFName("Test First 2");
        doctor2.setdLName("Test Last 2");
        doctor2.setType("Test type");
        doctor2 = doctorDao.createNewDoctor(doctor2);

        List<Doctor> doctors = doctorDao.getAllDoctors();
        assertEquals(2, doctors.size());
        assertTrue(doctors.contains(doctor));
        assertTrue(doctors.contains(doctor2));
    }

    @Test
    public void testDeleteDoctor(){
        Doctor doctor = new Doctor();
        doctor.setdFName("Test First");
        doctor.setdLName("Test Last");
        doctor.setType("Test type");
        doctor = doctorDao.createNewDoctor(doctor);

        doctorDao.deleteDoctor(doctor.getDid());
        try{
            Doctor fromDao = doctorDao.findDoctorById(doctor.getDid());
            fail("Expected data access exception");
        }catch(DataAccessException ex){

        }
    }

    @Test
    public void testUpdateDoctor(){
        Doctor doctor = new Doctor();
        doctor.setdFName("Test First");
        doctor.setdLName("Test Last");
        doctor.setType("Test type");
        doctor = doctorDao.createNewDoctor(doctor);

        Doctor doctorFromDao = doctorDao.findDoctorById(doctor.getDid());

        assertEquals(doctor, doctorFromDao);

        doctor.setdFName("Another First Name");

        doctorDao.updateDoctor(doctor.getDid(), doctor);
        assertNotEquals(doctor, doctorFromDao);

        doctorFromDao = doctorDao.findDoctorById(doctor.getDid());
        assertEquals(doctor, doctorFromDao);

    }

    @Test
    public void testGetPatientsByDoctor(){
        Doctor doctor = new Doctor();
        doctor.setdFName("Test First");
        doctor.setdLName("Test Last");
        doctor.setType("Test type");
        doctor = doctorDao.createNewDoctor(doctor);

        Patient patient = new Patient();
        patient.setpFName("PatientFirstName");
        patient.setpLName("PatientLastName");
        patient.setPhone("999-999-999");
        patient.setBirthDate(LocalDate.parse("2019-02-02"));
        patient = patientDao.createNewPatient(patient);

        patientDao.addPatientToDoctor(patient.getPid(), doctor.getDid());
        List<Patient> patientList = doctorDao.getPatientsByDoctor(doctor.getDid());
        assertTrue(patientList.contains(patient));
    }

}