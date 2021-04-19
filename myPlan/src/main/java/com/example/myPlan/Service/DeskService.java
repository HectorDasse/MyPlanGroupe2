package com.example.myPlan.Service;

import com.example.myPlan.Entities.Desk;
import com.example.myPlan.Repository.DeskRepository;

import java.util.Optional;

public class DeskService {


    public static boolean saveDesk(int numero, String comment, DeskRepository deskRepository){
        try {
            Desk desk = new Desk(numero, comment);
            deskRepository.save(desk);

            return true;
        } catch (Exception e){

            return false;
        }
    };

    public static boolean updateDesk(Desk desk, int numero, String comment, DeskRepository deskRepository){

        try {
            Optional<Desk> deskToUpdate = deskRepository.findById(desk.getId());
            if (deskToUpdate.isPresent()) {
                Desk deskUpdated = deskToUpdate.get();
                deskUpdated.setNumero(numero);
                deskUpdated.setComment(comment);

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
