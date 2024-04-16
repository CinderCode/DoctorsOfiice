package com.sg.doctorsoffice.model;

import java.time.LocalDate;
import java.util.Objects;

public class Patient {
    private int pid;
    private String pFName;
    private String pLName;
    private String phone;
    private LocalDate birthDate;
    private String medicalHistory;
    private String insurance;

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getpFName() {
        return pFName;
    }

    public void setpFName(String pFName) {
        this.pFName = pFName;
    }

    public String getpLName() {
        return pLName;
    }

    public void setpLName(String pLName) {
        this.pLName = pLName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return pid == patient.pid && Objects.equals(pFName, patient.pFName) && Objects.equals(pLName, patient.pLName) && Objects.equals(phone, patient.phone) && Objects.equals(birthDate, patient.birthDate) && Objects.equals(medicalHistory, patient.medicalHistory) && Objects.equals(insurance, patient.insurance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pid, pFName, pLName, phone, birthDate, medicalHistory, insurance);
    }
}