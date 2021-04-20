package com.example.myPlan.Controller;


import com.example.myPlan.Entities.Desk;
import com.example.myPlan.Entities.Device;
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

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping(path="/desk")
public class DeskController {

    @Autowired
    private DeskRepository deskRepository;

    @Autowired
    private DeviceRepository deviceRepository;


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
        List<Device> devices = deviceRepository.findAll();
        model.addAttribute("DevicesObject", devices);
        model.addAttribute("title", "Ajouter un bureau");
        model.addAttribute("appUserForm", form);

        return "addDesk";
    }

    // Show Register page.
    @RequestMapping(value = "/updateDesk", method = RequestMethod.GET)
    public String updateDesk(@RequestParam int id, Model model) {

        Optional<Desk> optionalDesk = deskRepository.findById(id);
        if (optionalDesk.isPresent()){
            Desk desk = optionalDesk.get();

            List<Device> devices = deviceRepository.findAll();
            model.addAttribute("DevicesObject", devices);
            model.addAttribute("title", "Ajouter un bureau");
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
                DeskService.saveDesk(appUserForm.getNumero(), appUserForm.getComment(), appUserForm.getDevices(), deskRepository);
            } else {
                //update

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
