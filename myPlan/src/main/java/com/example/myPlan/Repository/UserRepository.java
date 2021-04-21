package com.example.myPlan.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.myPlan.Entities.MyPlanUser;

public interface UserRepository extends JpaRepository<MyPlanUser, Integer>{

}
