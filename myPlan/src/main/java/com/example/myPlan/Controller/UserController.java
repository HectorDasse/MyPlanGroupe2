package com.example.myPlan.Controller;


import com.example.myPlan.Entities.Collaborator;
import com.example.myPlan.Entities.MyPlanUser;
import com.example.myPlan.Repository.UserRepository;
import com.example.myPlan.Service.CollaboratorService;
import com.example.myPlan.Service.UserService;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping(path="/user")
public class UserController {
	
	private UserRepository userRepository;

    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public String addUser(Model model) {
        MyPlanUser form = new MyPlanUser();
        
        model.addAttribute("title", "Ajouter un utilisateur");
        model.addAttribute("appUserForm", form);

        return "addUser";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String saveRegister(Model model, //
                               @ModelAttribute("appUserForm") @Validated MyPlanUser appUserForm, //
                               BindingResult result, //
                               final RedirectAttributes redirectAttributes) {

        try {

        	System.out.println("login : " + appUserForm.getUsername() + ", password : " + appUserForm.getPassword());
           	UserService.saveUser(appUserForm.getUsername(), appUserForm.getPassword(), userRepository);
            return "redirect:/";
        }
        // Other error!!
        catch (Exception e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            return "addUser";
        }
    }
    
    @RequestMapping(value = "/login")
    public String connect() {
    	return "connection";
    }
}
