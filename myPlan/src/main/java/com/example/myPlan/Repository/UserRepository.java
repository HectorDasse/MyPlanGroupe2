package com.example.myPlan.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myPlan.Entities.MyPlanUser;

public interface UserRepository extends JpaRepository<MyPlanUser, Integer>{

	Optional<MyPlanUser> findByUsernameAndPassword(String username, String password);

}
