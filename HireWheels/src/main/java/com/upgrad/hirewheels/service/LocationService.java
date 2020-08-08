package com.upgrad.hirewheels.service;

import com.upgrad.hirewheels.entities.Location;
import com.upgrad.hirewheels.exceptions.LocationNotFoundException;

public interface LocationService {

    public Location getLocationDetails(int id) throws LocationNotFoundException;
}
