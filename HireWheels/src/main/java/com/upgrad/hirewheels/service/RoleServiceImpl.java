package com.upgrad.hirewheels.service;

import com.upgrad.hirewheels.dao.RoleDAO;
import com.upgrad.hirewheels.entities.Role;
import com.upgrad.hirewheels.exceptions.RoleDetailsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("RoleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleDAO roleDAO;
    @Override
    public Role acceptUserTypeDetails(Role role) {
        return roleDAO.save(role);
    }

    @Override
    public Role getUserTypeDetails(int id) throws RoleDetailsNotFoundException {
        return roleDAO.findById(id).orElseThrow(
                ()->  new RoleDetailsNotFoundException("role not found for " + id));
    }


}
