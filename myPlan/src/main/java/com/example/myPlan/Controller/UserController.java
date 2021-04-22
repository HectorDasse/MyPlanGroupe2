package com.example.myPlan.Controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.myPlan.Entities.MyPlanUser;
import com.example.myPlan.Repository.UserRepository;
import com.example.myPlan.Service.UserService;


@Controller
@RequestMapping(path="/user")
public class UserController {
	
	@Autowired
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
    public String connect(Model model) {
        MyPlanUser form = new MyPlanUser();
        
        model.addAttribute("title", "Log in");
        model.addAttribute("appUserForm", form);
    	return "connection";
    }
    
    @RequestMapping(value = "/loginCheck", method = RequestMethod.POST)
    public String checkConnection(Model model, //
            @ModelAttribute("appUserForm") @Validated MyPlanUser appUserForm, //
            BindingResult result, //
            final RedirectAttributes redirectAttributes) {
    	System.out.println("1");
		Optional<MyPlanUser> optUser = userRepository.findByUsernameAndPassword(appUserForm.getUsername(), appUserForm.getPassword());
		System.out.println("2");
		if(optUser.isPresent()) {
			System.out.println("3");
			UserService.connectUser(optUser.get().getUserId(), optUser.get().getUsername(), optUser.get().getPassword(), userRepository);
			System.out.println("4");
	    	return "index";
		}
    	return "connection";
    }
}
