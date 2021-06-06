package com.example.playground;

public class grounds {
    public String email, groundname, location;//look at this at showing error so we cant make veriable like this .ok ??ok

    public grounds() {

    }

    public grounds(String email, String groundname, String location) {
        this.email = email;
        this.groundname = groundname;
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getgroundname() {
        return groundname;
    }

    public void setgroundname(String groundname) {
        this.groundname = groundname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
