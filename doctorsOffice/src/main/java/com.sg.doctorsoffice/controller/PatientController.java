package com.sg.doctorsoffice.controller;

import com.sg.doctorsoffice.model.Appointment;
import com.sg.doctorsoffice.model.Doctor;
import com.sg.doctorsoffice.model.Patient;
import com.sg.doctorsoffice.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
@CrossOrigin
public class PatientController {

    @Autowired
    PatientService patientService;

    @GetMapping("/getAll")
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable int id) {
        Patient result = patientService.getPatientById(id);
        if (result == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/create")
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient) {
        Patient result = patientService.addNewPatient(patient);
        if (result == null) {
            return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable int id, @RequestBody Patient patient) {
        Patient result = patientService.updatePatient(id, patient);
        if (result == null) {
            return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Patient> deletePatient(@PathVariable int id) {
        Patient result = patientService.deletePatientById(id);
        if (result == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getAppointments/{id}")
    public ResponseEntity<List<Appointment>> getAppointmentsByPatient(@PathVariable int id) {
        List<Appointment> result = patientService.getAppointmentsByPatient(id);
        if (result == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/{pid}/{did}")
    public ResponseEntity<Patient> insertPatientToDoctor(@PathVariable int pid, @PathVariable int did) {
        boolean result = patientService.insertPatientToDoctor(pid, did);
        if (!result) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{pid}/{did}")
    public ResponseEntity<Patient> deletePatientFromDoctor(@PathVariable int pid, @PathVariable int did) {
        boolean result = patientService.deletePatientFromDoctor(pid, did);
        if (!result) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
