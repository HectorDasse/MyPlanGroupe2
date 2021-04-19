package com.example.myPlan.Entities;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Collaborator {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String firstName;

	private String lastName;

    private Date enrollmentTime;

    private Date departureTime;
    
    public Collaborator(String firstName, String lastName, Date enrollmentTime, Date departureTime) {
    	super();
    	this.firstName = firstName;
    	this.lastName = lastName;
    	this.enrollmentTime = enrollmentTime;
    	this.departureTime = departureTime;
    }

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public Date getEmploymentTime() {
    	return enrollmentTime;
    }
    
    public void setEmploymentTime(Date employmentTime) {
    	this.enrollmentTime = employmentTime;
    }

    public Date getDepartureTime() {
    	return departureTime;
    }
    
    public void setDepartureTime(Date departureTime) {
    	this.departureTime = departureTime;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

	@Override
	public String toString() {
		return "Collaborator [" + (id != null ? "Id = " + id + ", " : "")
				+ (firstName != null ? "First name = " + firstName + ", " : "") + (lastName != null ? "Last name = " + lastName : "")
				+ (enrollmentTime != null ? "Employment time = " + enrollmentTime.toString() : "")
				+ (departureTime != null ? "Departure time = " + departureTime.toString() : "") + "]";
	}
}
