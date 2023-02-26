package com.project.service_provider.admin;

public class userHelperDoctor {
    String Name,Specialist,Email,Phone,Adress,curentUID,pic;


    public userHelperDoctor(String name, String specialist, String email, String phone, String adress, String curentUID, String pic) {
        Name = name;
        Specialist = specialist;
        Email = email;
        Phone = phone;
        Adress = adress;
        this.curentUID = curentUID;
        this.pic = pic;
    }

    public String getCurentUID() {
        return curentUID;
    }

    public void setCurentUID(String curentUID) {
        this.curentUID = curentUID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSpecialist() {
        return Specialist;
    }

    public void setSpecialist(String specialist) {
        Specialist = specialist;
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

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
