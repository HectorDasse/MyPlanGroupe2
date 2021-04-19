package com.example.myPlan.Entities;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Desk {

    @Id
    private Long id;

    public int numero;

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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
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
