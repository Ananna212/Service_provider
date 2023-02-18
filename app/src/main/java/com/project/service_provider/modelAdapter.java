package com.project.service_provider;

public class modelAdapter {
   private String adress,aera_of_study,designation,email,name,phone,picurl;

    public modelAdapter() {
    }

    public modelAdapter(String adress, String aera_of_study, String designation, String email, String name, String phone, String picurl) {
        this.adress = adress;
        this.aera_of_study = aera_of_study;
        this.designation = designation;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.picurl = picurl;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getAera_of_study() {
        return aera_of_study;
    }

    public void setAera_of_study(String aera_of_study) {
        this.aera_of_study = aera_of_study;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }
}