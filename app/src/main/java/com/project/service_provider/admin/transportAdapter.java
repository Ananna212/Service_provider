package com.project.service_provider.admin;

public class transportAdapter {
    private String drivingLnumber,location,mobileNumber,name,pic,vihicleNumber;

    public transportAdapter() {
    }

    public transportAdapter(String drivingLnumber, String location, String mobileNumber, String name, String pic, String vihicleNumber) {
        this.drivingLnumber = drivingLnumber;
        this.location = location;
        this.mobileNumber = mobileNumber;
        this.name = name;
        this.pic = pic;
        this.vihicleNumber = vihicleNumber;
    }

    public String getDrivingLnumber() {
        return drivingLnumber;
    }

    public void setDrivingLnumber(String drivingLnumber) {
        this.drivingLnumber = drivingLnumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getVihicleNumber() {
        return vihicleNumber;
    }

    public void setVihicleNumber(String vihicleNumber) {
        this.vihicleNumber = vihicleNumber;
    }
}
