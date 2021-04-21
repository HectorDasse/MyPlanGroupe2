package com.example.myPlan.Service;

import com.example.myPlan.Entities.Collaborator;
import com.example.myPlan.Entities.Desk;
import com.example.myPlan.Entities.Device;
import com.example.myPlan.Repository.CollaboratorRepository;
import com.example.myPlan.Repository.DeskRepository;
import com.example.myPlan.Repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

public class DeskService {

    public static boolean addDesk(int numero, String comment, List<Device> device, Collaborator collaborator, DeskRepository deskRepository){
        try {
            Desk desk = new Desk(numero, comment, device, collaborator);
            deskRepository.save(desk);

            return true;
        } catch (Exception e){

            return false;
        }
    };

    public static boolean updateDesk(int id, int numero, String comment, List<Device> device, Collaborator collaborator, DeskRepository deskRepository){

        try {
            Optional<Desk> deskToUpdate = deskRepository.findById(id);
            if (deskToUpdate.isPresent()) {
                Desk deskUpdated = deskToUpdate.get();
                deskUpdated.setNumero(numero);
                deskUpdated.setComment(comment);
                deskUpdated.setDevices(device);
                deskUpdated.setCollaborator(collaborator);

                deskRepository.save(deskUpdated);

            }
        } catch (Exception e) {
            return false;
        }
        return true;
    };

    public static boolean disaffected(Desk desk, DeskRepository deskRepository){

        try {
            desk.setCollaborator(null);
            deskRepository.save(desk);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public static boolean deleteDesk(Desk desk, DeskRepository deskRepository){
        try {
            System.out.println("delete");
            deskRepository.delete(desk);

        } catch (Exception e) {
            System.out.println("error " + e.getMessage());
            return false;
        }
        return true;


    }

    public static Model setModelFormulaire(Model model, Desk desk, String titre, DeviceRepository deviceRepository, CollaboratorRepository collaboratorRepository){
        List<Device> devices = deviceRepository.findAll();
        model.addAttribute("DevicesObject", devices);
        List<Collaborator> collaborator = (List<Collaborator>) collaboratorRepository.findAll();
        model.addAttribute("collaboratorObject", collaborator);
        model.addAttribute("title", titre);
        model.addAttribute("appUserForm", desk);

        return model;
    }

    public static Desk Find(int id, DeskRepository deskRepository) {

        Optional<Desk> optionalDesk = deskRepository.findById(id);
        if (optionalDesk.isPresent()){
            Desk desk = optionalDesk.get();
            return desk;
        } else {
            return null;
        }

    }

}
