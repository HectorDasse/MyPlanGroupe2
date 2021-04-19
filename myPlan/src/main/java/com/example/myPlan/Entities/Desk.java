package com.example.myPlan.Entities;

import javax.persistence.*;
import java.util.List;


@Entity
public class Desk {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    public int numero;

    @OneToOne
    public Device devices;

    public Device getDevices() {
        return devices;
    }

    public void setDevices(Device devices) {
        this.devices = devices;
    }

    public Desk(int numero, String comment, Device device) {
        this.numero = numero;
        this.comment = comment;
        this.devices = device;
    }

    public Desk() {

    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String comment;

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Desk{" +
                "id=" + id +
                ", numero=" + numero +
                ", comment='" + comment + '\'' +
                '}';
    }
}
