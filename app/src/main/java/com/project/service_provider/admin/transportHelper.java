package com.project.service_provider.admin;

public class transportHelper {
    private String  Name,VihicleNumber,MobileNumber,DrivingLNumber,Location,curentUID,Pic;

    public transportHelper(String name, String vihicleNumber, String mobileNumber, String drivingLNumber, String location, String curentUID, String pic) {
        Name = name;
        VihicleNumber = vihicleNumber;
        MobileNumber = mobileNumber;
        DrivingLNumber = drivingLNumber;
        Location = location;
        this.curentUID = curentUID;
        Pic = pic;
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

    public String getVihicleNumber() {
        return VihicleNumber;
    }

    public void setVihicleNumber(String vihicleNumber) {
        VihicleNumber = vihicleNumber;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        MobileNumber = mobileNumber;
    }

    public String getDrivingLNumber() {
        return DrivingLNumber;
    }

    public void setDrivingLNumber(String drivingLNumber) {
        DrivingLNumber = drivingLNumber;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getPic() {
        return Pic;
    }

    public void setPic(String pic) {
        Pic = pic;
    }
}
