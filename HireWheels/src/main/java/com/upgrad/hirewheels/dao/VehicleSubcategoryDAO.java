package com.upgrad.hirewheels.dao;

import com.upgrad.hirewheels.entities.VehicleSubcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("vehicleSubcategoryDAO")
public interface VehicleSubcategoryDAO extends JpaRepository<VehicleSubcategory,Integer> {
}
