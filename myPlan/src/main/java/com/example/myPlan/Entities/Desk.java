package com.example.myPlan.Entities;

import javax.persistence.*;
import java.util.List;


@Entity
public class Desk {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    public int numero;

    @OneToMany
    public List<Device> devices;

    @OneToOne
    public Collaborator collaborator;


    public String comment;



    public Desk(int numero, String comment, List<Device> device, Collaborator collaborator) {
        this.numero = numero;
        this.comment = comment;
        this.devices = device;
        this.collaborator = collaborator;
    }

    public Desk() {
//	@OneToOne
//    Collaborator collaborator = new Collaborator();

    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


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

	public Collaborator getCollaborator() {
		return collaborator;
	}

	public void setCollaborator(Collaborator collaborator) {
		this.collaborator = collaborator;
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
