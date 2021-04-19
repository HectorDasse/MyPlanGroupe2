package com.example.myPlan.Entities;

import java.util.Date;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Collaborator {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer Id;

    private String FirstName;

	private String LastName;

    private Date EnrollmentTime;

    private Date DepartureTime;
    
    public Collaborator(String firstName, String lastName, Date enrollmentTime, Date departureTime) {
    	super();
    	FirstName = firstName;
    	LastName = lastName;
    	EnrollmentTime = enrollmentTime;
    	DepartureTime = departureTime;
    }

	public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        this.Id = id;
    }
    
    public Date getEmploymentTime() {
    	return EnrollmentTime;
    }
    
    public void setEmploymentTime(Date employmentTime) {
    	EnrollmentTime = employmentTime;
    }

    public Date getDepartureTime() {
    	return DepartureTime;
    }
    
    public void setDepartureTime(Date departureTime) {
    	DepartureTime = departureTime;
    }
    
    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String name) {
        this.FirstName = name;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        this.LastName = lastName;
    }

	@Override
	public String toString() {
		return "Collaborator [" + (Id != null ? "Id = " + Id + ", " : "")
				+ (FirstName != null ? "First name = " + FirstName + ", " : "") + (LastName != null ? "Last name = " + LastName : "")
				+ (EnrollmentTime != null ? "Employment time = " + EnrollmentTime.toString() : "")
				+ (DepartureTime != null ? "Departure time = " + DepartureTime.toString() : "") + "]";
	}
}
