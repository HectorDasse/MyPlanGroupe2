package com.example.myPlan.Controller;


import com.example.myPlan.Entities.Collaborator;
import com.example.myPlan.Repository.CollaboratorRepository;
import com.example.myPlan.Service.CollaboratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;


@Controller
@RequestMapping(path="/collaborator")
public class CollaboratorController {

    @Autowired
    private CollaboratorRepository collaboratorRepository;

    // Show Register page.
    @RequestMapping(value = "/addCollaborator", method = RequestMethod.GET)
    public String addCollaborator(Model model) {

        Collaborator form = new Collaborator();
        model.addAttribute("title", "Ajouter un collaborateur");
        model.addAttribute("appUserForm", form);

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

}
