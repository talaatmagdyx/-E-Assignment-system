package com.example.talaatmagdy.education;

/**
 * Created by talaatmagdy on 5/23/17.
 */

public class Assignment {
    private String id_assignment;
    private String nameassignment;
    private String decription;
    private String movtiation;
    private String deadline;
    private String type;

    public Assignment() {

    }

    public Assignment(String id_assignment, String nameassignment, String decription, String movtiation, String deadline, String type) {
        this.id_assignment = id_assignment;
        this.nameassignment = nameassignment;
        this.decription = decription;
        this.movtiation = movtiation;
        this.deadline = deadline;
        this.type = type;
    }

    public String getId_assignment() {
        return id_assignment;
    }

    public String getNameassignment() {
        return nameassignment;
    }

    public void setNameassignment(String nameassignment) {
        this.nameassignment = nameassignment;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public String getMovtiation() {
        return movtiation;
    }

    public void setMovtiation(String movtiation) {
        this.movtiation = movtiation;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
