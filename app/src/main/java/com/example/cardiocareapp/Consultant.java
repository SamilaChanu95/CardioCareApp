package com.example.cardiocareapp;

public class Consultant {
    private int id;
    private String c_nic, c_first_name, c_last_name, c_gender, c_address, c_dob, c_phone_number, c_role, c_status;
    private int hospital_id, department_id, unit_id, ward_id;

    public Consultant(int id, String c_nic, String c_first_name, String c_last_name, String c_gender, String c_address, String c_dob, String c_phone_number, String c_role, String c_status, int hospital_id, int department_id, int unit_id, int ward_id) {
        this.id = id;
        this.c_nic = c_nic;
        this.c_first_name = c_first_name;
        this.c_last_name = c_last_name;
        this.c_gender = c_gender;
        this.c_address = c_address;
        this.c_dob = c_dob;
        this.c_role = c_role;
        this.c_status = c_status;
        this.c_phone_number = c_phone_number;
        this.hospital_id = hospital_id;
        this.department_id = department_id;
        this.unit_id = unit_id;
        this.ward_id = ward_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getC_nic() {
        return c_nic;
    }

    public void setC_nic(String c_nic) {
        this.c_nic = c_nic;
    }

    public String getC_first_name() {
        return c_first_name;
    }

    public void setC_first_name(String c_first_name) {
        this.c_first_name = c_first_name;
    }

    public String getC_last_name() {
        return c_last_name;
    }

    public void setC_last_name(String c_last_name) {
        this.c_last_name = c_last_name;
    }

    public String getC_gender() {
        return c_gender;
    }

    public void setC_gender(String c_gender) {
        this.c_gender = c_gender;
    }

    public String getC_address() {
        return c_address;
    }

    public void setC_address(String c_address) {
        this.c_address = c_address;
    }

    public String getC_dob() {
        return c_dob;
    }

    public void setC_dob(String c_dob) {
        this.c_dob = c_dob;
    }

    public String getC_phone_number() {
        return c_phone_number;
    }

    public void setC_phone_number(String c_phone_number) {
        this.c_phone_number = c_phone_number;
    }

    public String getC_role() {
        return c_role;
    }

    public void setC_role(String c_role) {
        this.c_role = c_role;
    }

    public String getC_status() {
        return c_status;
    }

    public void setC_status(String c_status) {
        this.c_status = c_status;
    }

    public int getHospital_id() {
        return hospital_id;
    }

    public void setHospital_id(int hospital_id) {
        this.hospital_id = hospital_id;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public int getUnit_id() {
        return unit_id;
    }

    public void setUnit_id(int unit_id) {
        this.unit_id = unit_id;
    }

    public int getWard_id() {
        return ward_id;
    }

    public void setWard_id(int ward_id) {
        this.ward_id = ward_id;
    }
}

