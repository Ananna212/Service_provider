package com.project.service_provider;

public class electricHelper {

    private String Name,Type,Email,Phone,Adress,currentuser,picurl;

    public electricHelper(String name, String type, String email, String phone, String adress, String currentuser, String picurl) {
        Name = name;
        Type = type;
        Email = email;
        Phone = phone;
        Adress = adress;
        this.currentuser = currentuser;
        this.picurl = picurl;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
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

    public String getCurrentuser() {
        return currentuser;
    }

    public void setCurrentuser(String currentuser) {
        this.currentuser = currentuser;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }
}
