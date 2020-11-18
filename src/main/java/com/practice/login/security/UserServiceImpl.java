package com.practice.login.security;

import com.practice.login.dao.userDao;
import com.practice.login.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserServiceImpl implements UserDetailsService {
    @Autowired
    userDao UserDao;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = UserDao.findByUsername(s);

        return user;
    }
}
