package com.upgrad.hirewheels.service;

import com.upgrad.hirewheels.entities.Role;
import com.upgrad.hirewheels.exceptions.RoleDetailsNotFoundException;

public interface RoleService {
    public Role acceptUserTypeDetails(Role role);
    public Role getUserTypeDetails(int id) throws RoleDetailsNotFoundException;


}
