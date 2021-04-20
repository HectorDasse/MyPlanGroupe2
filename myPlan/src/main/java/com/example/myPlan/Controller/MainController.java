package com.example.myPlan.Controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.myPlan.Entities.Collaborator;
import com.example.myPlan.Entities.Desk;
import com.example.myPlan.Entities.Device;
import com.example.myPlan.Repository.CollaboratorRepository;
import com.example.myPlan.Repository.DeskRepository;
import com.example.myPlan.Repository.DeviceRepository;


@Controller // This means that this class is a Controller
@RequestMapping(path="/") // This means URL's start with /demo (after Application path)
public class MainController {

	@Autowired
	private CollaboratorRepository collaboratorRepository;
	@Autowired
	private DeskRepository deskRepository;
	@Autowired
	private DeviceRepository deviceRepository;

	@GetMapping(path="/")
    public String index(Model model) {
        List<Collaborator> collaborator = (List<Collaborator>) collaboratorRepository.findAll();
        List<Desk> desk = (List<Desk>) deskRepository.findAll();
        List<Device> device = (List<Device>) deviceRepository.findAll();
        model.addAttribute("collaborator", collaborator);
        model.addAttribute("desk", desk);
        model.addAttribute("device", device);
        return "index";
    }
    
    @GetMapping(path="/toto")
    public @ResponseBody String toto() {
        // This returns a JSON or XML with the users
        return "toto";
    }

}