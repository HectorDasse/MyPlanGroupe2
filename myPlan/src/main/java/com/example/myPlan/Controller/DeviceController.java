package com.example.myPlan.Controller;

import com.example.myPlan.Entities.Collaborator;
import com.example.myPlan.Entities.Desk;
import com.example.myPlan.Entities.Device;
import com.example.myPlan.Repository.CollaboratorRepository;
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
    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private CollaboratorRepository collaboratorRepository;


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
        List<Desk> desks = (List<Desk>) deskRepository.findAll();
        model.addAttribute("desks", desks);
        List<Collaborator> collaborators = (List<Collaborator>) collaboratorRepository.findAll();
        model.addAttribute("collaborators", collaborators);

        return "addDevice";
    }

    // Show Register page.
    @RequestMapping(value = "/updateDevice", method = RequestMethod.GET)
    public String updateDevice(@RequestParam int id, Model model) {

        Optional<Device> optionalDevice = deviceRepository.findById(id);
        if (optionalDevice.isPresent()) {
            Device device = optionalDevice.get();

            model.addAttribute("title", "Ajouter une machine");
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

            List<Device> devices = deviceRepository.findAll();
            model.addAttribute("deviceList", devices);
            Device device = new Device();
            model.addAttribute("appUserForm", device);
            return "listDevices";
        }
        // Other error!!
        catch (Exception e) {
            System.out.println("error "  + e.getMessage());
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            return "addDevice";
        }
    }

    @RequestMapping(value = "/listDevices", method = RequestMethod.GET)
    public String listDevices(Model model) {
        List<Device> devices = deviceRepository.findAll();
        model.addAttribute("deviceList", devices);
        Device device = new Device();
        model.addAttribute("appUserForm", device);
        return "listDevices";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(Model model, //
                         @ModelAttribute("appUserForm") @Validated Device appUserForm, //
                         BindingResult result, //
                         final RedirectAttributes redirectAttributes) {

        // Validate result
        if (result.hasErrors()) {
            System.out.println("error Error: formulaire");
            model.addAttribute("errorMessage", "Error: formulaire");
            return "device/listDevices";
        }
        try {
            Optional<Device> optionalDevice = deviceRepository.findById(appUserForm.getId());
            if (optionalDevice.isPresent()){
                Device device = optionalDevice.get();
                DeviceService.deleteDevice(device, deviceRepository);
            } else {
                System.out.println("error device pas trouvé");
                model.addAttribute("errorMessage", "Error: device pas trouvé");
                return "redirect:device/listDevices";
            }

        }
        // Other error!!
        catch (Exception e) {
            System.out.println("error get device");
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            return "device/listDevices";
        }
        return "redirect:/device/listDevices";
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
            return "desk/listDesks";
        }
        try {
            Optional<Device> optionalDevice= deviceRepository.findById(appUserForm.getId());
            if (optionalDevice.isPresent()){
                Device device = optionalDevice.get();
                return "redirect:updateDevice?id=" + device.getId();
            } else {
                System.out.println("error desk pas trouvé\"");
                model.addAttribute("errorMessage", "Error: desk pas trouvé\"");
                return "redirect:/device/listDevices";
            }

        }
        // Other error!!
        catch (Exception e) {
            System.out.println("error get desk");
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            return "redirect:/device/listDevices";
        }
    }

}
