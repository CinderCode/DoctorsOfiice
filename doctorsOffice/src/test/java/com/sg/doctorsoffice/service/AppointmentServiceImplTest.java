package com.sg.doctorsoffice.service;

import com.sg.doctorsoffice.App;
import com.sg.doctorsoffice.dao.AppointmentDao;
import com.sg.doctorsoffice.dao.DoctorDao;
import com.sg.doctorsoffice.model.Appointment;
import com.sg.doctorsoffice.model.Doctor;
import com.sg.doctorsoffice.model.Patient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = App.class)
public class AppointmentServiceImplTest {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @Test
    public void testCreateAppointment(){

        //Test add appointment with a doctor not exist
        Patient patient = new Patient();
        patient.setpFName("Test pFName");
        patient.setpLName("Test pLName");
        patient.setPhone("773-876-0912");
        patient.setBirthDate(LocalDate.of(1998,9,26));
        patient.setMedicalHistory("Brain Surgery");
        patient.setInsurance("Aetna");
        patient = patientService.addNewPatient(patient);

        Appointment appointment = new Appointment();
        appointment.setDate(LocalDate.of(2024,1,22));
        appointment.setDescription("Leg broken");
        appointment.setPatient_id(patient.getPid());
        appointment.setDoctor_id(111);

        try {
            appointment = appointmentService.addNewAppointment(appointment);
        } catch (DataAccessException ex) {
            appointment = null;
        }
        assertNull(appointment);

        //Test add appointment with invalid date
        Patient patient2 = new Patient();
        patient2.setpFName("Test pFName");
        patient2.setpLName("Test pLName");
        patient2.setPhone("773-876-0912");
        patient2.setBirthDate(LocalDate.of(1998,9,26));
        patient2.setMedicalHistory("Brain Surgery");
        patient2.setInsurance("Aetna");
        patient2 = patientService.addNewPatient(patient2);

        Doctor doctor = new Doctor();
        doctor.setdFName("Test First");
        doctor.setdLName("Test Last");
        doctor.setType("Test type");
        doctor = doctorService.createNewDoctor(doctor);

        Appointment appointment2 = new Appointment();
        appointment2.setDate(LocalDate.of(1999,1,22));
        appointment2.setPatient_id(patient2.getPid());
        appointment2.setDoctor_id(doctor.getDid());
        appointment2.setDescription("Brain Surgery");

        appointment2 = appointmentService.addNewAppointment(appointment2);
        assertNull(appointment2);
    }

    @Test
    public void testUpdateAppointment(){
        // Test update appointment with ids not equal
        Appointment appointment = new Appointment();
        appointment.setAid(1);
        appointment.setDate(LocalDate.of(2024,9,22));
        appointment.setDescription("Leg broken");
        appointment.setPatient_id(1);
        appointment.setDoctor_id(2);
        appointment = appointmentService.updateAppointment(2, appointment);
        assertNull(appointment);

    }



}