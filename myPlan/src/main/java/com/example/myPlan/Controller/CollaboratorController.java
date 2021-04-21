package com.example.myPlan.Controller;


import com.example.myPlan.Entities.Collaborator;
import com.example.myPlan.Entities.Desk;
import com.example.myPlan.Entities.Device;
import com.example.myPlan.Repository.CollaboratorRepository;
import com.example.myPlan.Repository.DeviceRepository;
import com.example.myPlan.Repository.DeskRepository;
import com.example.myPlan.Service.CollaboratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping(path="/collaborator")
public class CollaboratorController {

    @Autowired
    private CollaboratorRepository collaboratorRepository;
    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private DeskRepository deskRepository;

    // Show Register page.
    @RequestMapping(value = "/addCollaborator", method = RequestMethod.GET)
    public String addCollaborator(Model model) {
        Collaborator form = new Collaborator();
        List<Desk> desks = (List<Desk>) deskRepository.findAll();
        List<Device> devices = (List<Device>) deviceRepository.findAll();
        
        model.addAttribute("title", "Ajouter un collaborateur");
        model.addAttribute("appUserForm", form);
        model.addAttribute("devices", devices);
        model.addAttribute("desks", desks);

        return "addCollaborator";
    }

    // Show Register page.
    @RequestMapping(value = "/updateCollaborator", method = RequestMethod.GET)
    public String updateCollaborator(@RequestParam int id, Model model) {

        Optional<Collaborator> optionalCollab = collaboratorRepository.findById(id);
        if (optionalCollab.isPresent()){
            Collaborator collaborator = optionalCollab.get();

            model.addAttribute("title", "Modifier le collaborateur");
            model.addAttribute("appUserForm", collaborator);

            return "addCollaborator";
        } else {
            return "Error";
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String saveRegister(Model model, //
                               @ModelAttribute("appUserForm") @Validated Collaborator appUserForm, //
                               BindingResult result, //
                               final RedirectAttributes redirectAttributes) {

//        // Validate result
//        if (result.hasErrors()) {
//        	System.out.println("An error occured");
//            return "addCollaborator";
//        }
        try {

            if (appUserForm.getId() == null){
            	CollaboratorService.saveCollaborator(appUserForm.getFirstName(), appUserForm.getLastName(), appUserForm.getEnrollmentTime(), appUserForm.getDepartureTime(), collaboratorRepository);
            } else {
                //update
                CollaboratorService.updateCollaborator(appUserForm, appUserForm.getFirstName(), appUserForm.getLastName(), appUserForm.getEnrollmentTime(), appUserForm.getDepartureTime(), collaboratorRepository);
            }
            Integer id = CollaboratorService.getCollaboratorByName(appUserForm.getFirstName(), appUserForm.getLastName(), collaboratorRepository).getId();
            return "redirect:/collaborator/updateCollaborator?id=" + id;
        }
        // Other error!!
        catch (Exception e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            return "addCollaborator";
        }
    }

    @RequestMapping(value = "/listCollaborators", method = RequestMethod.GET)
    public String listDesks(Model model) {
        List<Collaborator> collaborators = collaboratorRepository.findAll();
        model.addAttribute("collaboratorsList", collaborators);
        Collaborator collaborator = new Collaborator();
        model.addAttribute("appUserForm", collaborator);
        return "listCollaborators";
    }

}
