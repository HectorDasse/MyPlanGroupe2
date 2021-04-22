package com.example.myPlan.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.myPlan.Entities.Collaborator;
import com.example.myPlan.Entities.Desk;

@Repository
public interface DeskRepository extends JpaRepository<Desk, Integer> {
    List<Desk> findByCollaboratorLike(Collaborator collaborator);

    List<Desk> findByCollaboratorIsNull();
}