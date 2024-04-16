package com.sg.doctorsoffice.service;

import com.sg.doctorsoffice.App;
import com.sg.doctorsoffice.model.Doctor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = App.class)
class DoctorServiceImplTest {

    @Autowired
    DoctorService doctorService;

    @Test
    public void testCreateDoctor(){

        //Test add doctor with empty name
        Doctor doctor = new Doctor();
        doctor.setdFName("");
        doctor.setdLName("Test Last");
        doctor.setType("Type");
        doctor = doctorService.createNewDoctor(doctor);
        assertNull(doctor);

        //Test add doctor with empty type
        Doctor doctor2 = new Doctor();
        doctor2.setdFName("Test First");
        doctor2.setdLName("Test Last");
        doctor2.setType("");
        doctor2 = doctorService.createNewDoctor(doctor2);
        assertNull(doctor2);
    }

    @Test
    public void testUpdateDoctor(){
        // Test update doctor with ids not equal
        Doctor doctor = new Doctor();
        doctor.setDid(1);
        doctor.setdFName("Test First");
        doctor.setdLName("Test Last");
        doctor.setType("Type");
        doctor = doctorService.updateDoctor(2,doctor);
        assertNull(doctor);

        // test update doctor with invalid name
        Doctor doctor2 = new Doctor();
        doctor2.setDid(1);
        doctor2.setdFName("Test First");
        doctor2.setdLName("Test Last");
        doctor2.setType("");
        doctor2 = doctorService.updateDoctor(1, doctor2);
        assertNull(doctor2);
    }


}