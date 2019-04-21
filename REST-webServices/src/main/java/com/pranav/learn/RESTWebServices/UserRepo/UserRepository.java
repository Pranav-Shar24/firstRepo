package com.pranav.learn.RESTWebServices.UserRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pranav.learn.RESTWebServices.bean.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
