package com.example.myPlan.Repository;
import com.example.myPlan.Entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Integer> {
    Optional<Device> findByNameLike(String name);
}
