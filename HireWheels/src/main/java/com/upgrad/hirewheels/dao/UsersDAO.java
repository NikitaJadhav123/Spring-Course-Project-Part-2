package com.upgrad.hirewheels.dao;

import com.upgrad.hirewheels.entities.UsersData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("usersDAO")
public interface UsersDAO extends JpaRepository<UsersData,Integer> {

    Optional<UsersData> findByEmailId(String emailId);
    Optional<UsersData> findByMobileNo(String mobileNo);
    Optional<UsersData> findByPassword(String password);
  //  Optional<Users> findByEmailIdandPassword(String emailId,String password);
}
