package com.elfstaff.model;

import java.util.List;

/**
 * Created by DS on 9/20/2016.
 */
public class Staff {

    public Staff(){

    }


    String Name;
    String Phone;
    String password;
    String Email;
    String InstituionName;
    String InstituionID;
    String Location;
    String City;
    String CityId;
    String StaffID;
     List<Student> mStudentList;
    String Class;
    String ClassId;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getInstituionName() {
        return InstituionName;
    }

    public void setInstituionName(String instituionName) {
        InstituionName = instituionName;
    }

    public String getInstituionID() {
        return InstituionID;
    }

    public void setInstituionID(String instituionID) {
        InstituionID = instituionID;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getCityId() {
        return CityId;
    }

    public void setCityId(String cityId) {
        CityId = cityId;
    }

    public String getStaffID() {
        return StaffID;
    }

    public void setStaffID(String staffID) {
        StaffID = staffID;
    }


    public void setClass(String aClass) {
        Class = aClass;
    }

    public String getClassId() {
        return ClassId;
    }

    public void setClassId(String classId) {
        ClassId = classId;
    }
}
