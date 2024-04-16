package com.sg.doctorsoffice.model;

import java.util.Objects;

public class Doctor {
    private int did;
    private String dFName;
    private String dLName;
    private String type;

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public String getdFName() {
        return dFName;
    }

    public void setdFName(String dFName) {
        this.dFName = dFName;
    }

    public String getdLName() {
        return dLName;
    }

    public void setdLName(String dLName) {
        this.dLName = dLName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return did == doctor.did && Objects.equals(dFName, doctor.dFName) && Objects.equals(dLName, doctor.dLName) && Objects.equals(type, doctor.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(did, dFName, dLName, type);
    }
}