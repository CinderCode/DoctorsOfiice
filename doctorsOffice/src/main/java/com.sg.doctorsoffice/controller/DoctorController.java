package com.sg.doctorsoffice.controller;

import com.sg.doctorsoffice.model.*;
import com.sg.doctorsoffice.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
@CrossOrigin
public class DoctorController {
    @Autowired
    DoctorService doctorService;

    @GetMapping("/getAll")
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable int id) {
        Doctor result = doctorService.findDoctorById(id);
        if (result == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/create")
    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor) {
        Doctor result = doctorService.createNewDoctor(doctor);
        if (result == null) {
            return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(doctor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable int id, @RequestBody Doctor doctor) {
        Doctor result = doctorService.updateDoctor(id, doctor);
        if (result == null ) {
            return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Doctor> deleteDoctor(@PathVariable int id) {
        Doctor result = doctorService.deleteDoctorById(id);
        if (result == null ) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/getAppointments/{id}")
    public ResponseEntity<List<Appointment>> getAppointmentsByDoctor(@PathVariable int id) {
        List<Appointment> result = doctorService.getAppointmentsByDoctor(id);
        if (result == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getTotalAppointments/{id}")
    public ResponseEntity<DoctorAppointment> getTotalAppointmentsByDoctor(@PathVariable int id) {
        DoctorAppointment result = doctorService.getTotalAppointmentsByDoctor(id);
        if (result == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getTotalAppointmentsByMonth/{month}/{year}/{id}")
    public ResponseEntity<List<Appointment>> getTotalAppointmentsByMonth(@PathVariable int month,
                                                                         @PathVariable int year, @PathVariable int id) {
        List<Appointment> result = doctorService.getTotalAppointmentsByMonth(month, year, id);
        if (result == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getPatients/{id}")
    public ResponseEntity<List<Patient>> getPatientsByDoctor(@PathVariable int id) {
        List<Patient> result = doctorService.getPatientsByDoctor(id);
        if (result == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }

}
