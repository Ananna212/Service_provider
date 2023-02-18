package com.project.service_provider;

public class medicalAdapter {
    String name,specialist, phone, email,adress,pic;

    public medicalAdapter() {
    }

    public medicalAdapter(String name, String specialist, String phone, String email, String adress, String pic) {
        this.name = name;
        this.specialist = specialist;
        this.phone = phone;
        this.email = email;
        this.adress = adress;
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialist() {
        return specialist;
    }

    public void setSpecialist(String specialist) {
        this.specialist = specialist;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
