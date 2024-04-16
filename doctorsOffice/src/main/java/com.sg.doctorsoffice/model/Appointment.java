package com.sg.doctorsoffice.model;

import com.sg.doctorsoffice.App;

import java.time.LocalDate;
import java.util.Objects;

public class Appointment {
    private int aid;
    private LocalDate date;
    private int doctor_id;
    private int patient_id;
    private String description;

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment appointment = (Appointment) o;

        return aid == appointment.aid && Objects.equals(date, appointment.date) && doctor_id == appointment.doctor_id &&
                patient_id == appointment.patient_id && Objects.equals(description, appointment.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aid, date, doctor_id, patient_id, description);
    }
}