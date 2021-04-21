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
        	System.out.println("TRACE :  ");
        	e.printStackTrace();
        	System.out.println("login : " + login + ", password : " + password);
            return false;
        }
    }
}
