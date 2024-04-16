package com.sg.doctorsoffice.dao;

import com.sg.doctorsoffice.App;
import com.sg.doctorsoffice.model.Appointment;
import com.sg.doctorsoffice.model.Doctor;
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
class AppointmentDaoDBImplTest {

    @Autowired
    private AppointmentDao appointmentDao;

    @Autowired
    private PatientDao patientDao;

    @Autowired
    private DoctorDao doctorDao;

    @BeforeEach
    void setUp() {
        List<Appointment> appointments = appointmentDao.getAllAppointments();
        for(Appointment appointment : appointments){
            appointmentDao.deleteAppointment(appointment.getAid());
        }
    }

    @Test
    void testGetAppointmentWithIdNotFound() {

        Appointment appointment;
        try {
            appointment = appointmentDao.getAppointmentById(12);
        } catch (DataAccessException ex) {
            appointment = null;
        }
        assertNull(appointment);

    }

    @Test
    public void testAddAppointment(){

        Patient patient = new Patient();
        patient.setpFName("Test pFName");
        patient.setpLName("Test pLName");
        patient.setPhone("773-876-0912");
        patient.setBirthDate(LocalDate.of(1998,9,26));
        patient.setMedicalHistory("Brain Surgery");
        patient.setInsurance("Aetna");
        patient = patientDao.createNewPatient(patient);

        Doctor doctor = new Doctor();
        doctor.setdFName("Test First");
        doctor.setdLName("Test Last");
        doctor.setType("Test type");
        doctor = doctorDao.createNewDoctor(doctor);

        Appointment appointment = new Appointment();
        appointment.setDate(LocalDate.of(2024,1,22));
        appointment.setPatient_id(patient.getPid());
        appointment.setDoctor_id(doctor.getDid());
        appointment.setDescription("Brain Surgery");

        appointment = appointmentDao.addNewAppointment(appointment);

        Appointment appointmentFromDao = appointmentDao.getAppointmentById(appointment.getAid());

        assertEquals(appointment,appointmentFromDao);

    }

    @Test
    public void testGetAppointment(){

        Patient patient = new Patient();
        patient.setpFName("Test pFName");
        patient.setpLName("Test pLName");
        patient.setPhone("773-876-0912");
        patient.setBirthDate(LocalDate.of(1998,9,26));
        patient.setMedicalHistory("Brain Surgery");
        patient.setInsurance("Aetna");
        patient = patientDao.createNewPatient(patient);

        Doctor doctor = new Doctor();
        doctor.setdFName("Test First");
        doctor.setdLName("Test Last");
        doctor.setType("Test type");
        doctor = doctorDao.createNewDoctor(doctor);

        Appointment appointment = new Appointment();
        appointment.setDate(LocalDate.of(2024,1,22));
        appointment.setPatient_id(patient.getPid());
        appointment.setDoctor_id(doctor.getDid());
        appointment.setDescription("Brain Surgery");

        appointment = appointmentDao.addNewAppointment(appointment);

        Patient patient2 = new Patient();
        patient2.setpFName("Test pFName");
        patient2.setpLName("Test pLName");
        patient2.setPhone("773-876-0912");
        patient2.setBirthDate(LocalDate.of(1998,9,26));
        patient2.setMedicalHistory("Brain Surgery");
        patient2.setInsurance("Aetna");
        patient2 = patientDao.createNewPatient(patient2);

        Doctor doctor2 = new Doctor();
        doctor2.setdFName("Test First");
        doctor2.setdLName("Test Last");
        doctor2.setType("Test type");
        doctor2 = doctorDao.createNewDoctor(doctor2);

        Appointment appointment2 = new Appointment();
        appointment2.setDate(LocalDate.of(2024,1,22));
        appointment2.setPatient_id(patient2.getPid());
        appointment2.setDoctor_id(doctor2.getDid());
        appointment2.setDescription("Brain Surgery");

        appointment2 = appointmentDao.addNewAppointment(appointment2);

        List<Appointment> appointmentsList = appointmentDao.getAllAppointments();

        assertEquals(2,appointmentsList.size());
        assertTrue(appointmentsList.contains(appointment));
        assertTrue(appointmentsList.contains(appointment2));

    }

    @Test
    public void testGetAppointmentByDate() {

        Patient patient = new Patient();
        patient.setpFName("Test pFName");
        patient.setpLName("Test pLName");
        patient.setPhone("773-876-0912");
        patient.setBirthDate(LocalDate.of(1998,9,26));
        patient.setMedicalHistory("Brain Surgery");
        patient.setInsurance("Aetna");
        patient = patientDao.createNewPatient(patient);

        Doctor doctor = new Doctor();
        doctor.setdFName("Test First");
        doctor.setdLName("Test Last");
        doctor.setType("Test type");
        doctor = doctorDao.createNewDoctor(doctor);

        Appointment appointment = new Appointment();
        appointment.setDate(LocalDate.of(2024,1,22));
        appointment.setPatient_id(patient.getPid());
        appointment.setDoctor_id(doctor.getDid());
        appointment.setDescription("Brain Surgery");

        appointment = appointmentDao.addNewAppointment(appointment);

        List<Appointment> appointmentsList = appointmentDao.getAppointmentsByDate(LocalDate.of(2024, 1, 22));
        assertTrue(appointmentsList.contains(appointment));

        List<Appointment> appointmentsList2 = appointmentDao.getAppointmentsByDate(LocalDate.of(2024, 1, 21));
        assertFalse(appointmentsList2.contains(appointment));

    }

    @Test
    public void testDeleteAppointment(){

        Patient patient = new Patient();
        patient.setpFName("Test pFName");
        patient.setpLName("Test pLName");
        patient.setPhone("773-876-0912");
        patient.setBirthDate(LocalDate.of(1998,9,26));
        patient.setMedicalHistory("Brain Surgery");
        patient.setInsurance("Aetna");
        patient = patientDao.createNewPatient(patient);

        Doctor doctor = new Doctor();
        doctor.setdFName("Test First");
        doctor.setdLName("Test Last");
        doctor.setType("Test type");
        doctor = doctorDao.createNewDoctor(doctor);

        Appointment appointment = new Appointment();
        appointment.setDate(LocalDate.of(2024,1,22));
        appointment.setPatient_id(patient.getPid());
        appointment.setDoctor_id(doctor.getDid());
        appointment.setDescription("Brain Surgery");

        appointment = appointmentDao.addNewAppointment(appointment);

        Patient patient2 = new Patient();
        patient2.setpFName("Test pFName");
        patient2.setpLName("Test pLName");
        patient2.setPhone("773-876-0912");
        patient2.setBirthDate(LocalDate.of(1998,9,26));
        patient2.setMedicalHistory("Brain Surgery");
        patient2.setInsurance("Aetna");
        patient2 = patientDao.createNewPatient(patient2);

        Doctor doctor2 = new Doctor();
        doctor2.setdFName("Test First");
        doctor2.setdLName("Test Last");
        doctor2.setType("Test type");
        doctor2 = doctorDao.createNewDoctor(doctor2);

        Appointment appointment2 = new Appointment();
        appointment2.setDate(LocalDate.of(2024,1,22));
        appointment2.setPatient_id(patient2.getPid());
        appointment2.setDoctor_id(doctor2.getDid());
        appointment2.setDescription("Brain Surgery");

        appointment2 = appointmentDao.addNewAppointment(appointment2);

        List<Appointment> appointmentsList1 = appointmentDao.getAllAppointments();
        assertEquals(2,appointmentsList1.size());

        appointmentDao.deleteAppointment(appointment.getAid());

        List<Appointment> appointmentsList2 = appointmentDao.getAllAppointments();
        assertEquals(1, appointmentsList2.size());

        try {
            Appointment appointmentFromDao = appointmentDao.getAppointmentById(appointment.getAid());
            fail("Expected DataAccessException");
        } catch (DataAccessException ex) {
            System.out.println("Expected DataAccessException");
        }

    }

    @Test
    public void testUpdateAppointment(){

        Patient patient = new Patient();
        patient.setpFName("Test pFName");
        patient.setpLName("Test pLName");
        patient.setPhone("773-876-0912");
        patient.setBirthDate(LocalDate.of(1998,9,26));
        patient.setMedicalHistory("Brain Surgery");
        patient.setInsurance("Aetna");
        patient = patientDao.createNewPatient(patient);

        Doctor doctor = new Doctor();
        doctor.setdFName("Test First");
        doctor.setdLName("Test Last");
        doctor.setType("Test type");
        doctor = doctorDao.createNewDoctor(doctor);

        Appointment appointment = new Appointment();
        appointment.setDate(LocalDate.of(2024,1,22));
        appointment.setPatient_id(patient.getPid());
        appointment.setDoctor_id(doctor.getDid());
        appointment.setDescription("Brain Surgery");

        appointment = appointmentDao.addNewAppointment(appointment);

        Appointment appointmentFromDao = appointmentDao.getAppointmentById(appointment.getAid());
        assertEquals(appointment,appointmentFromDao);

        appointment.setDate(LocalDate.of(2024,2,29));
        appointment.setDescription("Leg Broken");

        appointmentDao.updateAppointment(appointment.getAid(), appointment);
        assertNotEquals(appointment, appointmentFromDao);

        appointmentFromDao = appointmentDao.getAppointmentById(appointment.getAid());
        assertEquals(appointment, appointmentFromDao);

    }
}