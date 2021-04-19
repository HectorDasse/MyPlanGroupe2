package com.example.myPlan.Entities;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Desk {

    @Id
    private Integer id;

    public int numero;

    public Desk(int numero, String comment) {
        this.numero = numero;
        this.comment = comment;
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
