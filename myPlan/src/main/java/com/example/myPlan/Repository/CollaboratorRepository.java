package com.example.myPlan.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myPlan.Entities.Collaborator;

public interface CollaboratorRepository extends JpaRepository<Collaborator, Integer>{

	Optional<Collaborator> findByLastNameAndFirstNameLike(String LastName, String FirstName);

}
