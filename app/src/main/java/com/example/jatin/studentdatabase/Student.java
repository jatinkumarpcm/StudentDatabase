package com.example.jatin.studentdatabase;

import java.io.Serializable;

/**
 * Created by jatin on 1/18/2018.
 */

public class Student implements Serializable {
    String name,course,contact,totalfee,feepaid;
    int id;

    public Student(String name, String course, String contact, String totalfee, String feepaid) {
        this.name = name;
        this.course = course;
        this.contact = contact;
        this.totalfee = totalfee;
        this.feepaid = feepaid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student(int id, String name, String course, String contact, String totalfee, String feepaid) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.contact = contact;
        this.totalfee = totalfee;
        this.feepaid = feepaid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getTotalfee() {
        return totalfee;
    }

    public void setTotalfee(String totalfee) {
        this.totalfee = totalfee;
    }

    public String getFeepaid() {
        return feepaid;
    }

    public void setFeepaidfeepaid(String paidfee) {
        this.feepaid = feepaid;
    }

    @Override
    public String toString() {
        return name;
    }
}
