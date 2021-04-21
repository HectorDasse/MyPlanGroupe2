package com.example.myPlan.Repository;


import com.example.myPlan.Entities.Collaborator;
import com.example.myPlan.Entities.Desk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeskRepository extends JpaRepository<Desk, Integer> {
    List<Desk> findByCollaboratorLike(Collaborator collaborator);
}