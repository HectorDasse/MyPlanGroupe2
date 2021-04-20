package com.example.myPlan.Controller;


import com.example.myPlan.Entities.Collaborator;
import com.example.myPlan.Entities.Desk;
import com.example.myPlan.Entities.Device;
import com.example.myPlan.Repository.CollaboratorRepository;
import com.example.myPlan.Repository.DeskRepository;
import com.example.myPlan.Repository.DeviceRepository;
import com.example.myPlan.Service.DeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sun.security.krb5.internal.crypto.Des;

import java.util.Optional;
import java.util.List;


@Controller
@RequestMapping(path="/desk")
public class DeskController {

    @Autowired
    private DeskRepository deskRepository;
    private DeviceRepository deviceRepository;

    @Autowired
    private CollaboratorRepository collaboratorRepository;


    @GetMapping(path="/toto")
    public @ResponseBody
    String toto() {
        // This returns a JSON or XML with the users
        return "totoalaplage";
    }

    // Show Register page.
    @RequestMapping(value = "/addDesk", method = RequestMethod.GET)
    public String addDesk(Model model) {

        Desk form = new Desk();
<<<<<<< HEAD
        model = DeskService.setModelFormulaire(model, form, deviceRepository, collaboratorRepository);

=======
        model.addAttribute("title", "Ajouter un bureaux");
        model.addAttribute("appUserForm", form);
>>>>>>> main

        return "addDesk";
    }

    // Show Register page.
    @RequestMapping(value = "/updateDesk", method = RequestMethod.GET)
    public String updateDesk(@RequestParam int id, Model model) {

        Optional<Desk> optionalDesk = deskRepository.findById(id);
        if (optionalDesk.isPresent()){
            Desk desk = optionalDesk.get();
<<<<<<< HEAD

            model = DeskService.setModelFormulaire(model, desk, deviceRepository, collaboratorRepository);
=======
            
            List<Device> devices = deviceRepository.findAll();
            model.addAttribute("DevicesObject", devices);
            model.addAttribute("title", "Ajouter un bureau");
            model.addAttribute("appUserForm", desk);
>>>>>>> main

            return "addDesk";
        } else {
            return "Error";
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String saveRegister(Model model, //
                               @ModelAttribute("appUserForm") @Validated Desk appUserForm, //
                               BindingResult result, //
                               final RedirectAttributes redirectAttributes) {

        // Validate result
        if (result.hasErrors()) {
            return "addDesk";
        }
        try {

            if (appUserForm.getId() == null) {
                DeskService.addDesk(appUserForm.getNumero(), appUserForm.getComment(), appUserForm.getDevices(), appUserForm.getCollaborator(), deskRepository);
            } else {
                //update
<<<<<<< HEAD

                if (DeskService.updateDesk(appUserForm.getId(), appUserForm.getNumero(), appUserForm.getComment(), appUserForm.getDevices(), appUserForm.getCollaborator(), deskRepository)){
                    return "redirect:/toto";
                } else {
                    model.addAttribute("errorMessage", "Error: " + "Item not found");
                    return "addDesk";
                }
=======
                DeskService.updateDesk(appUserForm, appUserForm.getNumero(), appUserForm.getComment(), appUserForm.getDevices(), deskRepository);
>>>>>>> main
            }
        }
        // Other error!!
        catch (Exception e) {
            System.out.println("error");
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            return "addDesk";
        }
        return "redirect:/toto";
    }

<<<<<<< HEAD
    @RequestMapping(value = "/listDesk", method = RequestMethod.GET)
    public String listDesk(Model model) {
        List<Desk> desks = deskRepository.findAll();
        model.addAttribute("deskList", desks);
        return "listDesk";
    }


=======
>>>>>>> main
}
