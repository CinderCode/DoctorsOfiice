package com.sg.doctorsoffice.dao;

import com.sg.doctorsoffice.App;
import com.sg.doctorsoffice.model.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = App.class)
class PatientDaoImplTest {

    @Autowired
    private PatientDao patientDao;

    @BeforeEach
    public void setUp() {
        List<Patient> patients = patientDao.getAllPatients();
        for(Patient patient : patients ){
            patientDao.deletePatient(patient.getPid());
        }

    }

    @Test
    public void testAddPatient(){

        Patient patient = new Patient();
        patient.setpFName("Test pFName");
        patient.setpLName("Test pLName");
        patient.setPhone("773-876-0912");
        patient.setBirthDate(LocalDate.of(1998,9,26));
        patient.setMedicalHistory("Brain Surgery");
        patient.setInsurance("Aetna");

        patient = patientDao.createNewPatient(patient);

        Patient patientFromDao = patientDao.getPatientById(patient.getPid());

       assertEquals(patient,patientFromDao);

    }

    @Test
    public void testGetPatient(){

        Patient patient = new Patient();
        patient.setpFName("Test pFName");
        patient.setpLName("Test pLName");
        patient.setPhone("773-876-0912");
        patient.setBirthDate(LocalDate.of(1998,9,26));
        patient.setMedicalHistory("Brain Surgery");
        patient.setInsurance("Aetna");

        patient = patientDao.createNewPatient(patient);

        Patient patient2 = new Patient();
        patient2.setpFName("Test pFName 2");
        patient2.setpLName("Test pLName 2");
        patient2.setPhone("312-166-0611");
        patient2.setBirthDate(LocalDate.of(1996,3,15));
        patient2.setMedicalHistory("Broken Leg");
        patient2.setInsurance("Blue Cross");
        patient2 = patientDao.createNewPatient(patient2);

        List<Patient> patientList = patientDao.getAllPatients();

        assertEquals(2,patientList.size());
        assertTrue(patientList.contains(patient));
        assertTrue(patientList.contains(patient2));


    }

    @Test
    public void testDeletePatient(){

        Patient patient = new Patient();
        patient.setpFName("Test pFName");
        patient.setpLName("Test pLName");
        patient.setPhone("773-876-0912");
        patient.setBirthDate(LocalDate.of(1998,9,26));
        patient.setMedicalHistory("Brain Surgery");
        patient.setInsurance("Aetna");
        patient = patientDao.createNewPatient(patient);

        Patient patient2 = new Patient();
        patient2.setpFName("Test pFName 2");
        patient2.setpLName("Test pLName 2");
        patient2.setPhone("312-166-0611");
        patient2.setBirthDate(LocalDate.of(1996,3,15));
        patient2.setMedicalHistory("Broken Leg");
        patient2.setInsurance("Blue Cross");
        patient2 = patientDao.createNewPatient(patient2);

        patientDao.deletePatient(patient.getPid());

        List<Patient> patientList = patientDao.getAllPatients();

        assertEquals(1,patientList.size());

        try {
            Patient patientFromDao = patientDao.getPatientById(patient.getPid());
            fail("Expected DataAccessException");
        } catch (DataAccessException ex) {

        }

    }

    @Test
    public void testUpdatePatient(){

        Patient patient = new Patient();
        patient.setPid(1);
        patient.setpFName("Test pFName");
        patient.setpLName("Test pLName");
        patient.setPhone("773-876-0912");
        patient.setBirthDate(LocalDate.of(1998,9,26));
        patient.setMedicalHistory("Brain Surgery");
        patient.setInsurance("Aetna");
        patient = patientDao.createNewPatient(patient);

        Patient patientFromDao = patientDao.getPatientById(patient.getPid());
        assertEquals(patient,patientFromDao);

        patient.setpFName("Changed pFName");
        int id = patient.getPid();
        patientDao.updatePatient(id,patient);
        assertNotEquals(patient, patientFromDao);

        patientFromDao = patientDao.getPatientById(patient.getPid());
        assertEquals(patient, patientFromDao);


    }


}
