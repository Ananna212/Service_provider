package com.project.service_provider.admin;

public class userHelper {
  private String  Name,Designation,Aera_of_study,Email,Phone,Adress,picurl;

    public userHelper(String name, String designation, String aera_of_study, String email, String phone, String adress, String picurl) {
        Name = name;
        Designation = designation;
        Aera_of_study = aera_of_study;
        Email = email;
        Phone = phone;
        Adress = adress;
        this.picurl = picurl;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String designation) {
        Designation = designation;
    }

    public String getAera_of_study() {
        return Aera_of_study;
    }

    public void setAera_of_study(String aera_of_study) {
        Aera_of_study = aera_of_study;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getAdress() {
        return Adress;
    }

    public void setAdress(String adress) {
        Adress = adress;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }
}
