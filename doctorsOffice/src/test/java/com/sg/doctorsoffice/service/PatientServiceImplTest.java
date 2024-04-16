package com.sg.doctorsoffice.service;

import com.sg.doctorsoffice.App;
import com.sg.doctorsoffice.model.Patient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = App.class)
public class PatientServiceImplTest {

    @Autowired
    PatientService patientService;

    @Test
    public void testAddPatient(){

        //Test patient with no first name
        Patient patient = new Patient();
        patient.setpFName("");
        patient.setpLName("Test pLName");
        patient.setPhone("773-876-0912");
        patient.setBirthDate(LocalDate.of(1998,9,26));
        patient.setMedicalHistory("Brain Surgery");
        patient.setInsurance("Aetna");
        patient = patientService.addNewPatient(patient);
        assertNull(patient);

        //Test patient with no last name
        Patient patient2 = new Patient();
        patient2.setpFName("Test pFName");
        patient2.setpLName("");
        patient2.setPhone("773-876-0912");
        patient2.setBirthDate(LocalDate.of(1998,9,26));
        patient2.setMedicalHistory("Brain Surgery");
        patient2.setInsurance("Aetna");
        patient2 = patientService.addNewPatient(patient2);
        assertNull(patient2);

        //Test patient with no bday
        Patient patient3 = new Patient();
        patient3.setpFName("Test pFName");
        patient3.setpLName("");
        patient3.setPhone("773-876-0912");
        patient3.setBirthDate(null);
        patient3.setMedicalHistory("Brain Surgery");
        patient3.setInsurance("Aetna");
        patient3 = patientService.addNewPatient(patient3);
        assertNull(patient3);

    }

    @Test
    public void testUpdatePatient(){

        //ids not equal
        Patient patient = new Patient();
        patient.setpFName("");
        patient.setpLName("Test pLName");
        patient.setPhone("773-876-0912");
        patient.setBirthDate(LocalDate.of(1998,9,26));
        patient.setMedicalHistory("Brain Surgery");
        patient.setInsurance("Aetna");
        patient = patientService.updatePatient(2,patient);
        assertNull(patient);

    }

}
