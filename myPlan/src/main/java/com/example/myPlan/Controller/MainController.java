package com.example.myPlan.Controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.myPlan.Entities.Collaborator;
import com.example.myPlan.Repository.CollaboratorRepository;


@Controller // This means that this class is a Controller
@RequestMapping(path="/") // This means URL's start with /demo (after Application path)
public class MainController {

	@Autowired
	private CollaboratorRepository collaboratorRepository;

	@GetMapping(path="/")
    public String index(Model model) {
        List<Collaborator> collaborator = (List<Collaborator>) collaboratorRepository.findAll();
        model.addAttribute("appUserForm", new Collaborator());
        model.addAttribute("collaboratorObject", collaborator);
        return "index";
    }
    
    @GetMapping(path="/toto")
    public @ResponseBody String toto() {
        // This returns a JSON or XML with the users
        return "toto";
    }

    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public String menu() {
        // This returns a JSON or XML with the users
        return "menu";
    }
}
