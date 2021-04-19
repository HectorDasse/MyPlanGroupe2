package com.example.myPlan.Service;

import com.example.myPlan.Entities.Desk;
import com.example.myPlan.Entities.Device;
import com.example.myPlan.Repository.DeskRepository;

import java.util.Optional;

public class DeskService {


    public static boolean saveDesk(int numero, String comment, Device device, DeskRepository deskRepository){
        try {
            Desk desk = new Desk(numero, comment, device);
            deskRepository.save(desk);

            return true;
        } catch (Exception e){

            return false;
        }
    };

    public static boolean updateDesk(Desk desk, int numero, String comment, Device device, DeskRepository deskRepository){

        try {
            Optional<Desk> deskToUpdate = deskRepository.findById(desk.getId());
            if (deskToUpdate.isPresent()) {
                Desk deskUpdated = deskToUpdate.get();
                deskUpdated.setNumero(numero);
                deskUpdated.setComment(comment);
                deskUpdated.setDevices(device);

                deskRepository.save(deskUpdated);

            }
        } catch (Exception e) {
            return false;
        }
        return true;
    };

    public static boolean deleteDesk(Desk desk, DeskRepository deskRepository){
        try {
            deskRepository.delete(desk);

        } catch (Exception e) {
            return false;
        }
        return true;


    }

}
