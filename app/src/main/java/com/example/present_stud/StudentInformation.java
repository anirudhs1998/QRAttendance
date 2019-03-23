package com.example.present_stud;

import java.util.ArrayList;

public class StudentInformation {
    private String uid,name,rollno,department,phone,password,email;
    ArrayList<Integer> subjects;
    public StudentInformation(){

    }

    public StudentInformation(String uid, String name, String rollno, String department, String phone, String email,String password, ArrayList<Integer> subjects) {
        this.uid = uid;
        this.name = name;
        this.rollno = rollno;
        this.department = department;
        this.phone = phone;
        this.email = email;
        this.password=password;
        this.subjects=subjects;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public ArrayList<Integer> getSubjects() {
        return subjects;
    }

    public void setSubjects(ArrayList<Integer> subjects) {
        this.subjects = subjects;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollno() {
        return rollno;
    }

    public void setRollno(String rollno) {
        this.rollno = rollno;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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

}
