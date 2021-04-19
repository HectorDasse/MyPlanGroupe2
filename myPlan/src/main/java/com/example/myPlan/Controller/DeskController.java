package com.example.myPlan.Controller;


import com.example.myPlan.Entities.Desk;
import com.example.myPlan.Repository.DeskRepository;
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


@Controller
@RequestMapping(path="/desk")
public class DeskController {

    @Autowired
    private DeskRepository deskRepository;


    @GetMapping(path="/toto")
    public @ResponseBody
    String toto() {
        // This returns a JSON or XML with the users
        return "totoalapage";
    }

    // Show Register page.
    @RequestMapping(value = "/addDesk", method = RequestMethod.GET)
    public String addDesk(Model model) {

        Desk form = new Desk();
        model.addAttribute("title", "Ajouter un bureaux");
        model.addAttribute("appUserForm", form);

        return "addDesk";
    }

    // Show Register page.
    @RequestMapping(value = "/updateDesk", method = RequestMethod.GET)
    public String updateDesk(@RequestParam int id, Model model) {

        Optional<Desk> optionalDesk = deskRepository.findById(id);
        if (optionalDesk.isPresent()){
            Desk desk = optionalDesk.get();

            model.addAttribute("title", "Ajouter un bureaux");
            model.addAttribute("appUserForm", desk);

            return "addDesk";
        } else {
            return "Error";
        }
    }


    // Show Register page.
    @RequestMapping(value = "/deleteDesk", method = RequestMethod.GET)
    public String deleteDesk(@RequestParam int id) {

        Optional<Desk> optionalDesk = deskRepository.findById(id);
        if (optionalDesk.isPresent()){
            Desk desk = optionalDesk.get();
            deskRepository.delete(desk);

            return "delete";
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

            if (appUserForm.getId() == null){
                DeskService.saveDesk(appUserForm.getNumero(), appUserForm.getComment(), deskRepository);
            } else {
                //update
                DeskService.updateDesk(appUserForm, appUserForm.getNumero(), appUserForm.getComment(), deskRepository);            }
        }
        // Other error!!
        catch (Exception e) {
            System.out.println("error");
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            return "addDesk";
        }
        return "redirect:/toto";
    }




}
