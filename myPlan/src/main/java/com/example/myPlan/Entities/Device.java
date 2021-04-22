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

<<<<<<< HEAD

=======
>>>>>>> 0bc813c5fd9fd800f7632b2196528f7946bb64f7
    public Device(String name, String type, String number) {
        this.name = name;
        this.type = type;
        this.number = number;
<<<<<<< HEAD

=======
>>>>>>> 0bc813c5fd9fd800f7632b2196528f7946bb64f7
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

<<<<<<< HEAD

=======
>>>>>>> 0bc813c5fd9fd800f7632b2196528f7946bb64f7
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
