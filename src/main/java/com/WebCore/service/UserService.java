package com.WebCore.service;

import com.WebCore.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.WebCore.model.User;

import java.util.HashSet;

@Service
public class UserService {

    @Autowired
    private DB_interact db_interact;

    @Autowired
    public BCryptPasswordEncoder bCryptPasswordEncoder;

    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        HashSet <Role> roles = new HashSet<Role>();
        Role role = new Role();
        role.setId(1);
        roles.add(role);
        //roles.add(db_interact.get_role(1));
        user.setRoles(roles);
        user.setRegistered_Date(
                new java.sql.Date(new java.util.Date().getTime()));
        db_interact.Save_user(user);
    }

    public User getByName(String username) {
    return db_interact.get_user(username);
    }

}
