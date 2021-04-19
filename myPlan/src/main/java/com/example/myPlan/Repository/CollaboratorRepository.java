package com.example.myPlan.Repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.myPlan.Entities.Collaborator;

public interface CollaboratorRepository extends CrudRepository<Collaborator, Integer>{

	Optional<Collaborator> findByLastNameAndFirstNameLike(String LastName, String FirstName);

}
