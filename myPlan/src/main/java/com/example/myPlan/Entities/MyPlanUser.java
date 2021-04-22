package com.example.myPlan.Entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Entity
public class MyPlanUser implements Serializable, UserDetails{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer userId;
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    private boolean isAccountNonExpired = false;
    private boolean isAccountNonLocked = false;
    private boolean isCredentialsNonExpired = false;
    private boolean isEnabled = false;

    
	public MyPlanUser() {
	}

	public MyPlanUser(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public MyPlanUser(Integer id, String username, String password, boolean isConnected) {
		this.userId = id;
		this.username = username;
		this.password = password;
	    isAccountNonExpired = isConnected;
	    isAccountNonLocked = isConnected;
	    isCredentialsNonExpired = isConnected;
	    isEnabled = isConnected;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.isAccountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.isAccountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.isCredentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return this.isEnabled;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
	
}
