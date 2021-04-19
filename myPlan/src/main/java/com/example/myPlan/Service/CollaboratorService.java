package com.example.myPlan.Service;

import java.util.Date;
import java.util.Optional;

import com.example.myPlan.Entities.Collaborator;
import com.example.myPlan.Repository.CollaboratorRepository;

public class CollaboratorService {
	public boolean saveCollaborator(String firstName, String lastName, Date enrollmentTime, Date departureTime, CollaboratorRepository collaboratorsRepository) {
        try {
        	Collaborator collaborators = new Collaborator (firstName, lastName, enrollmentTime, departureTime);
            collaboratorsRepository.save(collaborators);
            return true;
        }catch (Exception e) {
            return false;
        }
    }
	
    public boolean deleteCollaborator(Collaborator collaborators, CollaboratorRepository collaboratorsRepository) {
        try {
            collaboratorsRepository.delete(collaborators);
            return true;
        }catch (Exception e) {
            return false;
        }
    }
	
    public Collaborator getCollaboratorById(Integer id, CollaboratorRepository collaboratorsRepository) {
        try {
        	Optional<Collaborator> optCollab = collaboratorsRepository.findById(id);
        	return optCollab.get();
        }catch (Exception e) {
            return null;
        }
    }
	
//    public Collaborator getCollaboratorByName(String lastName, String firstName, CollaboratorRepository collaboratorsRepository) {
//        try {
//        	Optional<Collaborator> optCollab = collaboratorsRepository.findByLastNameAndFirstNameLike(lastName, firstName);
//        	return optCollab.get();
//        }catch (Exception e) {
//            return null;
//        }
//    }
}
