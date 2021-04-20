package com.example.myPlan.Controller;

import com.example.myPlan.Entities.Desk;
import com.example.myPlan.Entities.Device;
import com.example.myPlan.Entities.Device;
import com.example.myPlan.Repository.DeskRepository;
import com.example.myPlan.Repository.DeviceRepository;
import com.example.myPlan.Service.CollaboratorService;
import com.example.myPlan.Service.DeskService;
import com.example.myPlan.Service.DeviceService;
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
@RequestMapping(path = "/device")
public class DeviceController {

    @Autowired
    private DeskRepository deskRepository;
    private DeviceRepository deviceRepository;


    @GetMapping(path = "/toto")
    public @ResponseBody
    String toto() {
        // This returns a JSON or XML with the users
        return "totoalaplage";
    }

    // Show Register page.
    @RequestMapping(value = "/addDevice", method = RequestMethod.GET)
    public String addDevice(Model model) {

        Device form = new Device();
        model.addAttribute("title", "Ajouter un device");
        model.addAttribute("appUserForm", form);

        return "addDevice";
    }

    // Show Register page.
    @RequestMapping(value = "/updateDevice", method = RequestMethod.GET)
    public String updateDevice(@RequestParam int id, Model model) {

        Optional<Device> optionalDevice = deviceRepository.findById(id);
        if (optionalDevice.isPresent()) {
            Device device = optionalDevice.get();

            List<Device> devices = deviceRepository.findAll();
            model.addAttribute("DevicesObject", devices);
            model.addAttribute("title", "Ajouter un bureau");
            model.addAttribute("appUserForm", device);

            return "addDevice";
        } else {
            return "Error";
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String saveRegister(Model model, //
                               @ModelAttribute("appUserForm") @Validated Device appUserForm, //
                               BindingResult result, //
                               final RedirectAttributes redirectAttributes) {

        // Validate result
        if (result.hasErrors()) {
            return "addDevice";
        }
        try {

            if (appUserForm.getId() == null) {
                DeviceService.saveDevice(appUserForm.getName(), appUserForm.getType(), appUserForm.getNumber(), deviceRepository);
            } else {
                //update
                DeviceService.updateDevice(appUserForm, appUserForm.getName(), appUserForm.getType(), appUserForm.getNumber(), deviceRepository);
            }
            Integer id = DeviceService.getDeviceByName(appUserForm.getName(), deviceRepository).getId();
            return "redirect:/collaborator/updateCollaborator?id=" + id;
        }
        // Other error!!
        catch (Exception e) {
            System.out.println("error");
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            return "addDevice";
        }
    }
}
