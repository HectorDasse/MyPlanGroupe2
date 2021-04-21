package com.example.myPlan.Service;

import java.util.Date;
import java.util.Optional;

import com.example.myPlan.Entities.Collaborator;
import com.example.myPlan.Repository.CollaboratorRepository;

public class CollaboratorService {
	public static boolean saveCollaborator(String firstName, String lastName, Date enrollmentTime, Date departureTime, CollaboratorRepository collaboratorRepository) {
        try {
        	Collaborator collaborators = new Collaborator (firstName, lastName, enrollmentTime, departureTime);
            collaboratorRepository.save(collaborators);
            return true;
        }catch (Exception e) {
            return false;
        }
    }
	


    public static boolean updateCollaborator(Collaborator collaborator, String firstName, String lastName, Date enrollmentTime, Date departureTime, CollaboratorRepository collaboratorRepository) {
        try {
            Optional<Collaborator> collaboratorToUpdate = collaboratorRepository.findById(collaborator.getId());
            if (collaboratorToUpdate.isPresent()) {
            	Collaborator collaboratorUpdated = collaboratorToUpdate.get();
                collaboratorUpdated.setFirstName(firstName);
                collaboratorUpdated.setLastName(lastName);
                collaboratorUpdated.setEnrollmentTime(enrollmentTime);
                collaboratorUpdated.setDepartureTime(departureTime);

                collaboratorRepository.save(collaboratorUpdated);

            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }
	
    public static boolean deleteCollaborator(Collaborator collaborators, CollaboratorRepository collaboratorsRepository) {
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
	
    public static Collaborator getCollaboratorByName(String firstName, String lastName, CollaboratorRepository collaboratorsRepository) {
        try {
        	Optional<Collaborator> optCollab = collaboratorsRepository.findByLastNameAndFirstNameLike(lastName, firstName);
        	return optCollab.get();
        }catch (Exception e) {
            return null;
        }
    }


}
