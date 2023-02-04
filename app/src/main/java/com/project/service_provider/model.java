package com.project.service_provider;

public class model {
     private String Username,Email,Address,Pass,Confirmpass;

    public model(String username, String email, String address, String pass, String confirmpass) {
        Username = username;
        Email = email;
        Address = address;
        Pass = pass;
        Confirmpass = confirmpass;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }

    public String getConfirmpass() {
        return Confirmpass;
    }

    public void setConfirmpass(String confirmpass) {
        Confirmpass = confirmpass;
    }
}
