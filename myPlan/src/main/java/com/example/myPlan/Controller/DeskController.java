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

    @Autowired
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
        model = DeskService.setModelFormulaire(model, form, "Ajouter un bureau", deviceRepository, collaboratorRepository);

        return "addDesk";
    }

    // Show Register page.
    @RequestMapping(value = "/updateDesk", method = RequestMethod.GET)
    public String updateDesk(@RequestParam int id, Model model) {

        Optional<Desk> optionalDesk = deskRepository.findById(id);
        if (optionalDesk.isPresent()){
            Desk desk = optionalDesk.get();

            model = DeskService.setModelFormulaire(model, desk, "Modifier un bureau", deviceRepository, collaboratorRepository);


            return "addDesk";
        } else {
            return "Error";
        }
    }

    @RequestMapping(value = "/redirectUpdate", method = RequestMethod.POST)
    public String redirectUpdate(Model model, //
                         @ModelAttribute("appUserForm") @Validated Desk appUserForm, //
                         BindingResult result, //
                         final RedirectAttributes redirectAttributes) {

        // Validate result
        if (result.hasErrors()) {
            System.out.println("error");
            model.addAttribute("errorMessage", "Error: ");
            return "desk/listDesk";
        }
        try {
            Optional<Desk> optionalDesk = deskRepository.findById(appUserForm.getId());
            if (optionalDesk.isPresent()){
                Desk desk = optionalDesk.get();
                return "redirect:updateDesk?id=" + desk.getId();
            } else {
                System.out.println("error desk pas trouvé\"");
                model.addAttribute("errorMessage", "Error: desk pas trouvé\"");
                return "redirect:desk/listDesk";
            }

        }
        // Other error!!
        catch (Exception e) {
            System.out.println("error get desk");
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            return "desk/listDesk";
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

                if (DeskService.updateDesk(appUserForm.getId(), appUserForm.getNumero(), appUserForm.getComment(), appUserForm.getDevices(), appUserForm.getCollaborator(), deskRepository)){
                    return "redirect:/desk/listDesk";
                } else {
                    model.addAttribute("errorMessage", "Error: " + "Item not found");
                    return "addDesk";
                }
//                DeskService.updateDesk(appUserForm, appUserForm.getNumero(), appUserForm.getComment(), appUserForm.getDevices(), deskRepository);
            }
        }
        // Other error!!
        catch (Exception e) {
            System.out.println("error " + e.getMessage());
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            return "addDesk";
        }
        return "redirect:/desk/listDesk";
    }

    @RequestMapping(value = "/listDesk", method = RequestMethod.GET)
    public String listDesk(Model model) {
        List<Desk> desks = deskRepository.findAll();
        model.addAttribute("deskList", desks);
        Desk desk = new Desk();
        model.addAttribute("appUserForm", desk);
        return "listDesk";
    }

    @RequestMapping(value = "/listDeskCollaborateur", method = RequestMethod.GET)
    public String listDeskCollaborateur(@RequestParam int id,  Model model) {
        Optional<Collaborator> optionalCollaborator = collaboratorRepository.findById(id);

        if (optionalCollaborator.isPresent()){
            Collaborator collaborator = optionalCollaborator.get();
            List<Desk> desks = deskRepository.findByCollaboratorLike(collaborator);
            model.addAttribute("deskList", desks);
            Desk desk = new Desk();
            model.addAttribute("appUserForm", desk);
            return "listDeskCollaborator";
        }else {
            return "redirect:/desk/listDeskCollaborator";
        }
    }


    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(Model model, //
                               @ModelAttribute("appUserForm") @Validated Desk appUserForm, //
                               BindingResult result, //
                               final RedirectAttributes redirectAttributes) {

        // Validate result
        if (result.hasErrors()) {
            System.out.println("error Error: formulaire");
            model.addAttribute("errorMessage", "Error: formulaire");
            return "desk/listDesk";
        }
        try {
            Optional<Desk> optionalDesk = deskRepository.findById(appUserForm.getId());
            if (optionalDesk.isPresent()){
                Desk desk = optionalDesk.get();
                DeskService.deleteDesk(desk, deskRepository);
            } else {
                System.out.println("error desk pas trouvé");
                model.addAttribute("errorMessage", "Error: desk pas trouvé");
                return "redirect:desk/listDesk";
            }

        }
        // Other error!!
        catch (Exception e) {
            System.out.println("error get desk");
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            return "desk/listDesk";
        }
        return "redirect:/desk/listDesk";
    }


    @RequestMapping(value = "/deskDisaffected", method = RequestMethod.POST)
    public String deskDisaffected(Model model, //
                                 @ModelAttribute("appUserForm") @Validated Desk appUserForm, //
                                 BindingResult result, //
                                 final RedirectAttributes redirectAttributes) {

        // Validate result
        if (result.hasErrors()) {
            System.out.println("error");
            model.addAttribute("errorMessage", "Error: ");
            return "redirect:/desk/listDeskCollaborateur";
        }

        Desk desk = DeskService.Find(appUserForm.getId(), deskRepository);
        int idColab = desk.getCollaborator().getId();

        boolean answer = DeskService.disaffected(desk, deskRepository);
        if (answer){
            return "redirect:/desk/listDeskCollaborateur?id=" + idColab;
        } else {
            System.out.println("error desk pas trouvé");
            model.addAttribute("errorMessage", "Error: problemen enregistrement");
            return "redirect:/desk/listDeskCollaborateur?id=" + idColab;
        }
    }

}
