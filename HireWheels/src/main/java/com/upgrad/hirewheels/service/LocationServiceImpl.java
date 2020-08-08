package com.upgrad.hirewheels.service;

import com.upgrad.hirewheels.dao.LocationDAO;
import com.upgrad.hirewheels.entities.Location;
import com.upgrad.hirewheels.exceptions.LocationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service("LocationService")
public class LocationServiceImpl implements LocationService {

    @Autowired
    @Qualifier("locationDAO")
    private LocationDAO locationDAO;

    @Override
    public Location getLocationDetails(int id) throws LocationNotFoundException {
        Location location =locationDAO.findById(id).orElseThrow(
                ()->  new LocationNotFoundException("Location not found for " + id));
        return location;
    }

}
