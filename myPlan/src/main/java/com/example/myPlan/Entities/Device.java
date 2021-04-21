package com.example.myPlan.Entities;

import javax.persistence.*;

@Entity
public class Device {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String name;
    private String type; //interne/externe
    private String number;
    @OneToOne
    private Collaborator collaborator;
    @OneToOne
    private Desk desk;

    public Device(String name, String type, String number, Collaborator collaborator, Desk desk) {
        this.name = name;
        this.type = type;
        this.number = number;
        this.collaborator = collaborator;
        this.desk = desk;
    }

    public Device(String name, String type, String number, Desk desk) {
        this.name = name;
        this.type = type;
        this.number = number;
        this.desk = desk;
    }

    public Device() {

    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Collaborator getCollaborator() {
        return collaborator;
    }

    public void setCollaborator(Collaborator collaborator) {
        this.collaborator = collaborator;
    }

    public Desk getDesk() {
        return desk;
    }

    public void setDesk(Desk desk) {
        this.desk = desk;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
