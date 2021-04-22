package com.example.myPlan.Service;

import com.example.myPlan.Entities.MyPlanUser;
import com.example.myPlan.Repository.UserRepository;

public class UserService {
	public static boolean saveUser(String login, String password, UserRepository userRepository) {
        try {
        	MyPlanUser user = new MyPlanUser (login, password);
            userRepository.save(user);
            System.out.println("Saved");
            return true;
        }catch (Exception e) {
        	System.out.println("NOT Saved ! " + e.getMessage());
        	System.out.println("login : " + login + ", password : " + password);
            return false;
        }
    }
	
	public static boolean connectUser(Integer id, String username, String password, UserRepository userRepository) {
		System.out.println("01");
        try {
        	System.out.println("02");
        	MyPlanUser user = new MyPlanUser (id, username, password, true);
        	System.out.println("03");
            userRepository.save(user);
            System.out.println("Connected");
            return true;
        }catch (Exception e) {
        	System.out.println("NOT connected ! " + e.getMessage());
        	System.out.println("id : " + id + "login : " + username + ", password : " + password);
            return false;
        }
    }
}
