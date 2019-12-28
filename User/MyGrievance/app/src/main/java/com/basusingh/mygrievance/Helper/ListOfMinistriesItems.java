package com.basusingh.mygrievance.Helper;

import java.io.Serializable;

public class ListOfMinistriesItems implements Serializable {

    private String ministry_name, officer_name, officer_designation, address, email, phone;

    public String getMinistry_name() {
        return ministry_name;
    }

    public void setMinistry_name(String ministry_name) {
        this.ministry_name = ministry_name;
    }

    public String getOfficer_name() {
        return officer_name;
    }

    public void setOfficer_name(String officer_name) {
        this.officer_name = officer_name;
    }

    public String getOfficer_designation() {
        return officer_designation;
    }

    public void setOfficer_designation(String officer_designation) {
        this.officer_designation = officer_designation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
