package com.WeDemy.wedemy_demo.services;

import com.WeDemy.wedemy_demo.entity.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired UserService userService;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        MyUser myUser = userService.findUserByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new User(
                myUser.getUserName(),
                myUser.getPassword(),
                new ArrayList<>()
        );
    }

}
